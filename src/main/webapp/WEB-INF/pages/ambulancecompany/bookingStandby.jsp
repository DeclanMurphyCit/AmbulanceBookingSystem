<%--
  Created by IntelliJ IDEA.
  User: Declan
  Date: 23/02/2015
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/templating/include.jsp"%>

<link href="<c:url value="/WebResources/jqueryui/jquery-uimin.css" />" rel="stylesheet">

<style>
    .col-md-9{
        width: 65%;
        text-align: left;
        margin-left: -25px;
    }

    .col-md-2{

        width: 35%;
        text-align: right;
        font-weight: bold;

    }
</style>

<div id="main-panel" align="center" style="width:480px;">
    <h2>${message}</h2>

    <h3><span id="numBookings" class="label label-warning">Number of active bookings: ${numberOfBookings}</span></h3>

    <c:forEach var="ab" items="${bookings}">

    <c:choose>
    <c:when test="${ab.urgent == true}">
    <div class="alert alert-danger panel-body" role="alert" id="booking-${ab.bookingId}">
        </c:when>
        <c:otherwise>
        <div id="booking-${ab.bookingId}" class="panel-body alert-info">
            </c:otherwise>
            </c:choose>



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

            <div class="col-md-2" align="right" style="margin-top: 5px" >Assigned Crew:</div>
            <div class="col-md-9" align="left" style="display: flex">
                <select class="form-control" onchange="assignCrew(${ab.bookingId})" id="assignedCrew-${ab.bookingId}">
                    <option value="-1"></option>
                    <c:forEach var="crew" items="${crews}">
                        <option  <c:if test="${ab.ambCrewId == crew.id}"> selected </c:if>
                                value="${crew.id}">${crew.crewIdentifier}</option>
                    </c:forEach>
                </select>
                <span class="" id="assigned-${ab.bookingId}" aria-hidden="false" style="margin-top: 7px"></span>


            </div>

            <div class="col-md-6" style="alignment: center; margin-top: 15px">
                <button class="btn btn-success" style="width: 135px; display: none;" id="confirm-${ab.bookingId}" onclick="closeDialog(${ab.bookingId})">Confirm</button>
            </div>
            <div class="col-md-6" style="alignment: center; margin-top: 15px">
                <button class="btn btn-danger" style="width: 135px;" id="deny-${ab.bookingId}" onclick="cancelBooking(${ab.bookingId})">Cancel Booking</button>
            </div>
            <hr>
        </div>


        </c:forEach>
    </div>
    <div id="dialog"></div>
    <script src="<%= request.getContextPath() %>/WebResources/jquery-1.11.2.min.js"></script>
    <script src="<%= request.getContextPath() %>/WebResources/bootstrap/js/bootstrap.min.js"></script>

    <script src="<%= request.getContextPath() %>/WebResources/jqueryui/jquery-ui.min.js"></script>


    <script type="text/javascript">
        var bookingIdArray = [ ${bookingIdArray}];
        var numBookings = ${numberOfBookings};
        var numBookingsUpdated = 0;

        if(numBookings ==0) jQuery("#numBookings").removeClass("label-warning").addClass("label-primary");

        function assignCrew(bookingId) {

            var crewId = jQuery("#assignedCrew-"+bookingId).val();
            jQuery("#confirm-"+bookingId).attr("disabled", false);

            jQuery.ajax({
                type: "POST",
                url: '<%=request.getContextPath()%>/ambcompany/assignCrew',
                data: ({bookingId : bookingId,crewId : crewId}),
                success: function(data) {
                    if(data == "success")
                    {
                        jQuery("#assigned-"+bookingId).addClass("glyphicon glyphicon-ok");

                    } else if ( data == "notLoggedIn")
                    {
                        alert("Please login before assigning a crew");
                    }
                },
                error: function(e){
                    alert('Error: ' + e);
                }
            });
        }

        function cancelBooking(bookingId) {

            jQuery("#dialog").html("Are you sure you want to cancel this booking?<br>Another ambulance company will be assigned to transfer the patient.");
            // Define the Dialog and its properties.
            jQuery("#dialog").dialog({
                resizable: false,
                modal: true,
                title: "Alert",
                height: 195,
                width: 380,
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

            var dialogOpen = false;

            jsonBookingArray.forEach(function(obj) { //If a transfer was rejected by a crew a dialog appears ensuring that a new crew is selected
                if(obj.status == 6){
                    dialogOpen = true;
                    jQuery("#confirm-"+obj.bookingId).show().attr("disabled", true);
                    var ambReg = jQuery("#assignedCrew-"+obj.bookingId).find(":selected").text();

                    jQuery("#assignedCrew-"+obj.bookingId + " option:selected").attr('disabled','disabled')
                            .siblings().removeAttr('disabled');

                    jQuery("#dialog").focus();
                    jQuery("#booking-"+obj.bookingId).dialog({
                        width: "515px",
                        resizable: false,
                        close: function() {
                            location.reload();
                            dialogOpen = false;
                        },
                        title: "Crew Cancellation: Please select another ambulance crew"
                    });
                }
            });


            setInterval(function() { //Checks for updates
                numBookingsUpdated = 0;
                jQuery.ajax({
                    type: "GET",
                    dataType: 'json',
                    url: '<%=request.getContextPath()%>/ambcompany/getNewBookings',
                    success: function (data) {
                        if (data != "({})") {
                            jQuery.each(data, function (index, element) {
                                numBookingsUpdated++;

                                //Reload this page if a new booking is made which does not appear on this page
                                if (arrayContains(element.bookingId) == false)
                                    location.reload();

                                //Reload this page if a booking was cancelled (booking status mismatch)
                                jsonBookingArray.forEach(function (obj) {
                                    if (element.bookingId == obj.bookingId) {
                                        if (obj.status != element.status && dialogOpen == false)
                                        {
                                            if(element.status != 2)
                                                location.reload();
                                        }

                                    }
                                });
                            });
                        }
                        if(numBookings != numBookingsUpdated)
                            location.reload();
                    },
                    error: function (e) {
                        console.log('Error: ' + e);
                    }
                });
            }, 5000);
        });

        function closeDialog(bookingId)
        {
            jQuery('#booking-'+bookingId).dialog('close');
        }

        function arrayContains(id)
        {
            return (bookingIdArray.indexOf(id) > -1);
        }
        var jsonBookingArray =  ${jsonBookingArray};

    </script>