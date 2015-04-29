<%--
  Created by IntelliJ IDEA.
  User: Declan
  Date: 17/02/2015
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/templating/include.jsp"%>

<link href="<c:url value="/WebResources/jqueryui/jquery-uimin.css" />" rel="stylesheet">

<style>
  .col-xs-7{
    margin: 0 -0px;
  }

  .buttonsRow{

    margin-left: auto;
    margin-right: auto;
    width: inherit;
    margin-top: 15px;
  }

  .alert-danger, .alert-info{
    margin-left: 3px;
  }

  .bold-text{
    font-weight: bold;
    margin: 0 -25px;
  }
</style>

<div align="center">
  <h3><span id="numBookings"  class="label label-warning">Number of pending bookings: ${numberOfBookings}</span></h3>
</div>
<c:forEach var="ab" items="${bookings}">
  <c:choose>
    <c:when test="${ab.urgent == true}">
      <div class="alert alert-danger panel-body" role="alert" id="booking-${ab.bookingId}">
    </c:when>
    <c:otherwise>
      <div class="panel-body alert-info" id="booking-${ab.bookingId}">
    </c:otherwise>
  </c:choose>

  <div >

    <div class="row">
      <div class="col-xs-6 bold-text" align="right">Patient:</div>
      <div class="col-xs-7" align="left"><c:out value="${patients[ab.bookingId]}"/></div>
    </div>

    <div class="row">
      <div class="col-xs-6 bold-text" align="right">Origin:</div>
      <div class="col-xs-7" align="left">
        <c:forEach var="location" items="${locations}">
          <c:if test="${ab.origin == location.id}">
            ${ location.name}
          </c:if>
        </c:forEach>
      </div>
    </div>

    <div class="row">
      <div class="col-xs-6 bold-text" align="right">Destination:</div>
      <div class="col-xs-7" align="left">
        <c:forEach var="location" items="${locations}">
          <c:if test="${ab.destination == location.id}">
            ${ location.name}
          </c:if>
        </c:forEach>
      </div>
    </div>

    <div class="row">
      <div class="col-xs-6 bold-text" align="right">Cardiac:</div>
      <div class="col-xs-7" align="left">
        <c:choose>
          <c:when test="${ab.cardiac == true}">
            Yes
          </c:when>
          <c:otherwise>
            No
          </c:otherwise>
        </c:choose>
      </div>
    </div>

    <div class="row">
      <div class="col-xs-6 bold-text" align="right">Urgent:</div>
      <div class="col-xs-7" align="left">
        <c:choose>
          <c:when test="${ab.urgent == true}">
            Yes
          </c:when>
          <c:otherwise>
            No
          </c:otherwise>
        </c:choose>
      </div>
    </div>

    <div class="row">
      <div class="col-xs-6 bold-text" align="right">Transfer Date:</div>
      <div class="col-xs-7" align="left"> ${ab.dateOfTransfer} </div>
    </div>

    <div class="buttonsRow">
      <div class="col-xs-7" id="arrived-origin-${ab.bookingId}" style="margin-top: 15px;
      <c:if test="${ab.status == 3}"> display: none; </c:if> " >
        <button class="btn btn-success" style="width: 160px;" id="accept-${ab.bookingId}" onclick="setBookingStatus(${ab.bookingId},3)">Arrived at Origin</button>
      </div>

      <div class="col-xs-7" id="arrived-dest-${ab.bookingId}"  style="margin-top: 15px;
      <c:if test="${ab.status == 2}"> display: none; </c:if> ">
        <button class="btn btn-success" style="width: 160px;" id="accept-${ab.bookingId}" onclick="setBookingStatus(${ab.bookingId},5)">Arrived at Destination</button>
      </div>

      <div class="col-xs-5" style="margin-top: 15px">
        <button class="btn btn-danger" style="width: 70px;" id="deny-${ab.bookingId}" onclick="cancelBooking(${ab.bookingId},6)">Cancel</button>
      </div>
    </div>
  </div>
  </div>
  <hr>
</c:forEach>
<div id="dialog"></div>

<script src="<%= request.getContextPath() %>/WebResources/jquery-1.11.2.min.js"></script>
<script src="<%= request.getContextPath() %>/WebResources/jqueryui/jquery-ui.min.js"></script>
<script src="<%= request.getContextPath() %>/WebResources/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">
  var bookingIdArray = [ ${bookingIdArray}];
  var numBookings = ${numberOfBookings};
  var numBookingsUpdated = 0;

  function cancelBooking(bookingId,status) {

    jQuery("#dialog").html("Are you sure you want to cancel this transfer?");
    // Define the Dialog and its properties.
    jQuery("#dialog").dialog({
      resizable: false,
      modal: true,
      title: "Alert",
      height: 175,
      width: 300,
      buttons: {
        "Yes": function () {
          jQuery(this).dialog('close');

          setBookingStatus(bookingId,status);

        },
        "No": function () {
          jQuery(this).dialog('close');
        }
      }
    });


  }

  function setBookingStatus(bookingId,status) {
    console.log(status);
    jQuery.ajax({
      type: "POST",
      url: '<%=request.getContextPath()%>/ambcompany/setBookingStatus',
      data: ({bookingId : bookingId,status:status}),
      success: function(data) {
        if(data == "success")
        {
          if(status == 5)
          {
            jQuery('#booking-'+bookingId).fadeOut(300, function() { jQuery(this).remove(); });
            numBookings--;
            if(numBookings ==0)
            {
              jQuery("#numBookings").removeClass("label-warning").addClass("label-primary");
              jQuery("#numBookings").text("No currently assigned transfers");
            }
            else
              jQuery("#numBookings").text("Number of pending transfers: " +numBookings);
          } else {
            jQuery("#arrived-origin-"+bookingId).hide();
            jQuery("#arrived-dest-"+bookingId).show();
          }

        } else if ( data == "notLoggedIn")
        {
          alert("Please login before accepting a transfer");
        }
      },
      error: function(e){
        console.log('Error: ' + e);
      }
    });
  }

  jQuery(document).ready(function () {

    if(numBookings ==0)
    {
      jQuery("#numBookings").removeClass("label-warning").addClass("label-primary");
      jQuery("#numBookings").html("No currently assigned transfers");
    }

    setInterval(function() {
      numBookingsUpdated = 0;

      jQuery.ajax({
        type: "GET",
        dataType: 'json',
        url: '<%=request.getContextPath()%>/ambcompany/getNewCrewBookings',
        success: function (data) {
          if (data != "({})") {
            jQuery.each(data, function(index, element) {
              numBookingsUpdated++;
              if(arrayContains(element.bookingId)== false) //if an id is not in the array
                location.reload();
            });
          }

          if(numBookings != numBookingsUpdated)
            location.reload();

        },
        error: function (e) {
          console.log("error" + e);
        }
      });
    }, 5000);
  });

  function arrayContains(id)
  {
    return (bookingIdArray.indexOf(id) > -1);
  }

</script>