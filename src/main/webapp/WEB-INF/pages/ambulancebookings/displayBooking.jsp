<%--
  Created by IntelliJ IDEA.
  User: Declan
  Date: 13/02/2015
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/templating/include.jsp"%>
<div id="main-panel" align="center" style="width:700px;">
    <h2>${message}</h2>

    <table data-role="table" class="ui-responsive" data-mode="columntoggle" id="bookingTable">
        <thead>
        <tr>
            <th data-priority="1">Booking ID</th>
            <th data-priority="2">Destination</th>
            <th data-priority="3">Origin</th>
            <th data-priority="4">Cardiac</th>
            <th data-priority="5">Urgent</th>
            <th data-priority="6">Transfer Date/Time</th>
            <th data-priority="7">Created Date/Time</th>

        </tr>
        </thead>

        <tbody>
        <tr>
            <td>${id}</td>
            <td>${ambulancebooking.origin}</td>
            <td>${ambulancebooking.destination}</td>
            <td>${ambulancebooking.cardiac}</td>
            <td>${ambulancebooking.urgent}</td>
            <td>${ambulancebooking.dateOfTransfer}</td>
            <td>${ambulancebooking.dateCreated}</td>
        </tr>
        </tbody>
    </table>
</div>

<script src="<%= request.getContextPath() %>/WebResources/jquery-1.11.2.min.js"></script>
<script src="<%= request.getContextPath() %>/WebResources/bootstrap/js/bootstrap.min.js"></script>