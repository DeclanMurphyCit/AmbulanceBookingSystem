<%--
  Created by IntelliJ IDEA.
  User: Declan
  Date: 17/02/2015
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/templating/include.jsp"%>

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

    <h3><span id="numBookings" class="label label-warning">Number of pending bookings: ${numberOfBookings}</span></h3>

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

            <div class="col-md-7" style="margin-top: 15px">
                <button class="btn btn-success" style="width: 75px;" id="accept-${ab.bookingId}" onclick="setBookingApproval(${ab.bookingId},'accept')">Accept</button>
            </div>
            <div class="col-md-4" style="margin-top: 15px">
                <button class="btn btn-danger" style="width: 75px;" id="deny-${ab.bookingId}" onclick="setBookingApproval(${ab.bookingId},'deny')">Deny</button>
            </div>
            <hr>
        </div>


    </c:forEach>
</div>

<script src="<%= request.getContextPath() %>/resources/jquery-1.11.2.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">
    var numBookings = ${numberOfBookings};
    if(numBookings ==0) jQuery("#numBookings").removeClass("label-warning").addClass("label-primary");
    function setBookingApproval(bookingId,approval) {
        jQuery.ajax({
            type: "POST",
            url: '<%=request.getContextPath()%>/ambbooking/'+approval+'Booking',
            data: ({bookingId : bookingId}),
            success: function(data) {
                if(data == "success")
                {
                    jQuery('#booking-'+bookingId).fadeOut(300, function() { jQuery(this).remove(); });
                    numBookings--;
                    if(numBookings ==0)
                    {
                        jQuery("#numBookings").removeClass("label-warning").addClass("label-primary");
                        jQuery("#numBookings").text("No bookings require approval");
                    }
                    else
                        jQuery("#numBookings").text("Number of pending bookings: " +numBookings);

                }
            },
            error: function(e){
                alert('Error: ' + e);
            }
        });
    }

    jQuery(document).ready(function () {
    setInterval(function() {
            jQuery.ajax({
                type: "GET",
                url: '<%=request.getContextPath()%>/ambbooking/getNewUnapprovedBookings',
                //data: ({}),
                success: function (data) {
                    if (data != "none") {
                        //alert(data);
                    }
                },
                error: function (e) {
                    //alert('Error: ' + e);
                }
            });
        }, 5000);
    });

</script>