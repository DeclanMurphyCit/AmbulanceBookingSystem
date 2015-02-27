<%--
  Created by IntelliJ IDEA.
  User: Declan
  Date: 23/02/2015
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Declan
  Date: 17/02/2015
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/templating/include.jsp"%>

<link href="<c:url value="/resources/jqueryui/jquery-ui.min.css" />" rel="stylesheet">

<style>
    .col-md-9{
        width: 65%;
        text-align: left;
    }

    .col-md-2{
        width: 35%;
        text-align: right;
        font-weight: bold;
    }
</style>

<div id="main-panel" align="center" style="width:450px;">
    <h2>${message}</h2>

    <h3><span id="numBookings" class="label label-warning">Number of active bookings: ${numberOfBookings}</span></h3>

    <c:forEach var="ab" items="${bookings}">

        <div class="panel-body" id="booking-${ab.bookingId}">

            <div class="col-md-2" align="right">Patient:</div>
            <div class="col-md-9" align="left"><c:out value="${patients[ab.bookingId]}"/></div>

            <div class="col-md-2" align="right">Origin:</div>
            <div class="col-md-9" align="left">
                <c:forEach var="location" items="${locations}">
                    <c:if test="${ab.origin == location.id}">
                        ${ location.name}
                    </c:if>
                </c:forEach>
            </div>

            <div class="col-md-2" align="right">Destination:</div>
            <div class="col-md-9" align="left">
                <c:forEach var="location" items="${locations}">
                    <c:if test="${ab.destination == location.id}">
                        ${ location.name}
                    </c:if>
                </c:forEach>
            </div>

            <div class="col-md-2" align="right">Cardiac:</div>
            <div class="col-md-9" align="left">
                <c:choose>
                    <c:when test="${ab.cardiac == true}">
                        Yes
                    </c:when>
                    <c:otherwise>
                        No
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="col-md-2" align="right">Urgent:</div>
            <div class="col-md-9" align="left">
                <c:choose>
                    <c:when test="${ab.urgent == true}">
                        Yes
                    </c:when>
                    <c:otherwise>
                        No
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="col-md-2" align="right">Transfer Date:</div>
            <div class="col-md-9" align="left"> ${ab.dateOfTransfer} </div>

            <div class="col-md-2" align="right">Date Created:</div>
            <div class="col-md-9" align="left"> ${ab.dateCreated} </div>

            <div class="col-md-11" style="margin-top: 15px">
                <button class="btn btn-danger" style="width: 155px;" id="deny-${ab.bookingId}" onclick="cancelBooking(${ab.bookingId})">Cancel Booking</button>
            </div>
            <hr>
        </div>


    </c:forEach>
</div>
<div id="dialog"></div>
<script src="<%= request.getContextPath() %>/resources/jquery-1.11.2.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/jqueryui/jquery-ui.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">
    var bookingIdArray = [ ${bookingIdArray}];
    var numBookings = ${numberOfBookings};
    if(numBookings ==0) jQuery("#numBookings").removeClass("label-warning").addClass("label-primary");


    function cancelBooking(bookingId) {

        jQuery("#dialog").html("Are you sure you want to cancel this booking?");
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
                    jQuery.ajax({
                        type: "POST",
                        url: '<%=request.getContextPath()%>/ambcompany/cancelBooking',
                        data: ({bookingId : bookingId}),
                        success: function(data) {
                            if(data == "success")
                            {
                                jQuery('#booking-'+bookingId).fadeOut(300, function() { jQuery(this).remove(); });
                                numBookings--;
                                if(numBookings ==0)
                                {
                                    jQuery("#numBookings").removeClass("label-warning").addClass("label-primary")
                                        .text("No bookings require approval");
                                }
                                else
                                    jQuery("#numBookings").text("Number of active bookings: " +numBookings);

                            }
                        },
                        error: function(e){
                            alert('Error: ' + e);
                        }
                    });
                },
                "No": function () {
                    jQuery(this).dialog('close');

                }
            }
        });
    }

    jQuery(document).ready(function () {
        setInterval(function() {
            jQuery.ajax({
                type: "GET",
                dataType: 'json',
                url: '<%=request.getContextPath()%>/ambcompany/getNewBookings',
                //data: ({}),
                success: function (data) {
                    if (data != "none") {
                        jQuery.each(data, function(index, element) {
                            if(arrayContains(element.bookingId)== false)
                                location.reload();
                        });
                    }
                },
                error: function (e) {
                  //  alert('Error: ' + e);
                }
            });
        }, 5000);
    });

    function arrayContains(id)
    {
        return (bookingIdArray.indexOf(id) > -1);
    }

</script>