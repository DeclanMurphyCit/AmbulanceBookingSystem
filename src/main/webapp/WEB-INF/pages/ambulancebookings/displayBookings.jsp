<%--
  Created by IntelliJ IDEA.
  User: Declan
  Date: 13/02/2015
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/templating/include.jsp"%>

<!-- DataTables CSS -->
<link href="<c:url value="/WebResources/datatables/media/css/jquery.dataTables.min.css"/>" rel="stylesheet">

<style>
    #bookingsTable tbody tr {
        max-height: 15px; /* or whatever height you need to make them all consistent */
    }

    #bookingsTable {
        max-height: 80%;
    }

    th {

        border-bottom: 1px solid #d6d6d6;
        text-align: center;
        font-size: 12px;
        width: auto;
    }
    td{
        font-size: 12px;
        width: auto;
    }
    tr{
        text-align: center;
    }
</style>

<div id="main-panel" align="center" style="width:100%">
    <h3>${title}</h3>
    <div id="loading" align="center">
        <img src="<c:url value="/WebResources/images/loading_spinner.gif" />" style="width:75px;height:75px">
    </div>
    <table data-role="table" class="ui-responsive" data-mode="columntoggle" id="bookingsTable" style="display: none;">
        <thead>
        <tr>

            <th>Patient</th>
            <th>Origin</th>
            <th>Destination</th>
            <th>Status</th>
            <th>Urgent</th>
            <th>Cardiac</th>
            <th>Approved</th>
            <th>Approved By</th>
            <th>Transfer Date</th>
            <th>Date Created</th>
            <th>Created By</th>
            <th>Assigned Company</th>
            <th>Assigned Crew</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="ab" items="${bookings}">
            <tr <c:if test="${ab.urgent == true && ab.status != 5}"> style="color: red" </c:if> >

                <td><c:out value="${patients[ab.bookingId]}"/></td>
                <c:forEach var="location" items="${locations}">
                    <c:if test="${ab.origin == location.id}">
                        <td>${ location.name}</td>
                    </c:if>
                </c:forEach>
                <c:forEach var="location" items="${locations}">
                    <c:if test="${ab.destination == location.id}">
                        <td>${ location.name}</td>
                    </c:if>
                </c:forEach>
                <td>
                    <c:choose>
                        <c:when test="${ab.status == 0}">
                            Pending ADON Approval
                        </c:when>
                        <c:when test="${ab.status == 1}">
                            Pending Crew Assignment
                        </c:when>
                        <c:when test="${ab.status == 2}">
                            Pending Patient Pickup
                        </c:when>
                        <c:when test="${ab.status == 3}">
                            Pending Patient Dropoff
                        </c:when>
                        <c:when test="${ab.status == 4}">
                            Booking Rejected by ADON
                        </c:when>
                        <c:when test="${ab.status == 5}">
                            Transfer Completed
                        </c:when>
                        <c:when test="${ab.status == 6}">
                            Crew Cancelled Pending Reassignment
                        </c:when>
                    </c:choose>
                </td>
                <c:choose>
                    <c:when test="${ab.urgent == true}">
                        <td>Yes</td>
                    </c:when>
                    <c:otherwise>
                        <td>No</td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${ab.cardiac == true}">
                        <td>Yes</td>
                    </c:when>
                    <c:otherwise>
                        <td>No</td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${ab.approved == true}">
                        <td>Yes</td>
                    </c:when>
                    <c:otherwise>
                        <td>No</td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${ab.approvedBy == -1}">
                        <td>-</td>
                    </c:when>
                    <c:otherwise>
                        <td><c:out value="${users[ab.approvedBy]}"/></td>
                    </c:otherwise>
                </c:choose>
                <td>${ab.dateOfTransfer}</td>
                <td>${ab.dateCreated}</td>
                <td><c:out value="${users[ab.createdBy]}"/></td>
                <c:choose>
                    <c:when test="${(empty ab.ambCompanyId)  || (ab.ambCompanyId ==-1) || (ab.ambCompanyId == 0)}">
                        <td>-</td>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="company" items="${companies}">
                            <c:if test="${ab.ambCompanyId == company.id}">
                                <td>${ company.name}</td>
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${(empty ab.ambCrewId) || (ab.ambCrewId ==-1) || (ab.ambCrewId == 0)}">
                        <td>-</td>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="crew" items="${crews}">
                            <c:if test="${ab.ambCrewId == crew.id}">
                                <td>${ crew.crewIdentifier}<c:if test="${ab.status == 6}"> [Cancelled]</c:if> </td>
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>

<script src="<%= request.getContextPath() %>/WebResources/jquery-1.11.2.min.js"></script>
<script src="<%= request.getContextPath() %>/WebResources/bootstrap/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath() %>/WebResources/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="<%= request.getContextPath() %>/WebResources/datatables/media/js/dataTables.bootstrap.js"></script>
<script src="<%= request.getContextPath() %>/WebResources/datatables/media/js/jquery.datatables.datesort.js"></script>

<script type="text/javascript">
    jQuery(document).ready( function () {

        jQuery('#bookingsTable').dataTable( {
            "bJQueryUI": true,
            /*"sScrollY": "330px",*/
            "bScrollCollapse": false,
            "bPaginate": false,
            //"bFilter": false,
            "bProcessing": true,
            "bDeferRender": false,
            //    "aaSortingFixed": [[ 0, 'desc' ]],
            "aoColumnDefs": [
                // {"bSortable": false, "aTargets": [ 2,7,8,9 ]},//Turn of sorting on cols 5,6
                { "sType": "datetime-eu", "aTargets": [ 3 ] }//Set Column as Datetime-eu for sorting
            ],
            "aaSorting": [[ 8, "asc" ]]
        });

        jQuery("#loading").hide();
        jQuery("#bookingsTable").show();
    } );
</script>
