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
<link rel="stylesheet" type="text/css"
      href="<% request.getContextPath();%>/resources/datatables/media/css/jquery.dataTables.min.css">

<style>
    #bookingsTable tbody tr {
        max-height: 15px; /* or whatever height you need to make them all consistent */
        font-size: small;
    }

    #bookingsTable {
        max-height: 90%;
        font-size: small;
    }

    th {
        width: 1px;
        border-bottom: 1px solid #d6d6d6;
    }
    tr{
        text-align: center;
    }
</style>

<div id="main-panel" align="center" style="width:850px;">
    <c:if test="${not empty bookings}">
        <table data-role="table" class="ui-responsive" data-mode="columntoggle" id="bookingsTable">
            <thead>
            <tr>

                <th>Patient</th>
                <th>Destination</th>
                <th>Origin</th>
                <th>Cardiac</th>
                <th>Urgent</th>
                <th>Transfer Date</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="ab" items="${bookings}">
                <tr>
                    <td><c:out value="${patients[ab.bookingId]}"/></td>
                    <c:forEach var="location" items="${locations}">
                        <c:if test="${ab.destination == location.id}">
                            <td>${ location.name}</td>
                        </c:if>
                    </c:forEach>
                    <c:forEach var="location" items="${locations}">
                        <c:if test="${ab.origin == location.id}">
                            <td>${ location.name}</td>
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${ab.cardiac == true}">
                            <td>Yes</td>
                        </c:when>
                        <c:otherwise>
                            <td>No</td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${ab.urgent == true}">
                            <td>Yes</td>
                        </c:when>
                        <c:otherwise>
                            <td>No</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${ab.dateOfTransfer}</td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </c:if>
</div>

<script src="<%= request.getContextPath() %>/resources/jquery-1.11.2.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/datatables/media/js/dataTables.bootstrap.js"></script>
<script src="<%= request.getContextPath() %>/resources/datatables/media/js/jquery.datatables.datesort.js"></script>

<script type="text/javascript">
    jQuery(document).ready( function () {

        jQuery('#bookingsTable').dataTable( {
        "bJQueryUI": true,
                /*"sScrollY": "330px",*/
                "bScrollCollapse": false,
                "bPaginate": false,
                "bFilter": false,
                "bProcessing": true,
                "bDeferRender": false,
            //    "aaSortingFixed": [[ 0, 'desc' ]],
                "aoColumnDefs": [
           // {"bSortable": false, "aTargets": [ 2,7,8,9 ]},//Turn of sorting on cols 5,6
            { "sType": "datetime-eu", "aTargets": [ 3 ] }//Set Column as Datetime-eu for sorting
        ],
                "aaSorting": [[ 5,6, "desc" ]]/*,
                "fnDrawCallback": function ( oSettings ) {
            if ( oSettings.aiDisplay.length == 0 ) {
                return;
            }
            var nTrs = $('#table tbody tr');
            var iColspan = nTrs[0].getElementsByTagName('td').length;
            var sLastGroup = "";
            for ( var i=0 ; i<nTrs.length ; i++ ) {
                var iDisplayIndex = oSettings._iDisplayStart + i;
                var sGroup = oSettings.aoData[ oSettings.aiDisplay[iDisplayIndex] ]._aData[0];
                if ( sGroup != sLastGroup ) {
                    var nGroup = document.createElement( 'tr' );
                    var nCell = document.createElement( 'td' );
                    nCell.colSpan = iColspan;
                    nCell.className = "group";
                    nCell.innerHTML = sGroup;
                    nGroup.appendChild( nCell );
                    nTrs[i].parentNode.insertBefore( nGroup, nTrs[i] );
                    sLastGroup = sGroup;
                }
            }
        }*/
    });
    } );
</script>
