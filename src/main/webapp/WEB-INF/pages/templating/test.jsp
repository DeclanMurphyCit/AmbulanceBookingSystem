<%--
  Created by IntelliJ IDEA.
  User: Declan
  Date: 12/02/2015
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/templating/include.jsp"%>

<link href="<c:url value="/WebResources/jqueryui/jquery-uimin.css" />" rel="stylesheet">
<link href="<c:url value="/WebResources/datetimepicker/datepicker.css" />" rel="stylesheet">


<form:form method="post" class="form-horizontal" id="bookingForm" action="addNewBooking" modelAttribute="ambulancebooking">
    <div id="main-panel" align="center" style="width:500px;">
        <div id="loading" align="center">
            <img src="/WebResources/images/loading_spinner.gif" style="width:75px;height:75px">
        </div>
        <div id="loadingComplete" style="display: none;">
            <form:errors path="*" cssClass="errorblock" element="div" />

            <div class="form-group">
                <label class="control-label col-xs-2" for="wardId" >Ward</label>
                <div class="col-xs-10">
                    <select class="form-control" id="wardId" required="required" onchange="getWardList()" >
                        <option value="" label=""/>
                        <c:forEach var="ward" items="${wardList}">
                            <option value="${ward.id}">${ward.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-xs-2" for="patientId" path="patientId">Patient</form:label>
                <div class="col-xs-10">
                    <form:select class="form-control" id="patientId" path="patientId" required="required">
                        <form:option value="" label=""/>
                        <%--<c:forEach var="pat" items="${patientList}">
                            <form:option value="${pat.id}" label="${pat.firstName} ${pat.lastName}"/>
                        </c:forEach>--%>
                        <form:errors path="patientId" cssClass="error" class="notification error" style="display:block"></form:errors>
                    </form:select>
                </div>
            </div>

            <div class="ui-field-contain form-group">
                <form:label class="control-label col-xs-2" for="origin" path="origin">Origin</form:label>
                <div class="col-xs-10">
                    <form:select class="form-control" id="origin" path="origin" required="required">
                        <form:option value="" label=""/>
                        <c:forEach var="org" items="${locationList}">
                            <form:option value="${org.id}" label="${org.name}"/>
                        </c:forEach>
                        <form:errors path="origin" cssClass="error" class="notification error" style="display:block"></form:errors>
                    </form:select>
                </div>
            </div>

            <div class="ui-field-contain form-group">
                <form:label class="control-label col-xs-2" for="destination" path="destination">Destination </form:label>
                <div class="col-xs-10">
                    <form:select class="form-control" id="destination" path="destination" required="required">
                        <form:option value="" label=""/>
                        <c:forEach var="dest" items="${locationList}">
                            <form:option value="${dest.id}" label="${dest.name}"/>
                        </c:forEach>
                        <form:errors path="destination" cssClass="error" class="notification error" style="display:block"></form:errors>
                    </form:select>
                </div>
            </div>

            <div class="ui-field-contain form-group">
                <form:label class="control-label col-xs-2" for="cardiac" path="cardiac">Cardiac</form:label>
                <div class="col-xs-10" align="left">
                    <form:checkbox path="cardiac"/>
                    <form:errors path="cardiac" cssClass="error" class="notification error" style="display:block"></form:errors>
                </div>
            </div>

            <div class="ui-field-contain form-group">
                <form:label class="control-label col-xs-2" for="urgent" path="urgent">Urgent</form:label>
                <div class="col-xs-10" align="left">
                    <form:checkbox id="urgent" path="urgent"/>
                    <form:errors path="urgent" cssClass="error" class="notification error" style="display:block"></form:errors>
                </div>
            </div>


            <div class="ui-field-contain form-group" id="dateDiv">
                <form:label class="control-label col-xs-2" for="dateOfTransfer" path="dateOfTransfer">Date</form:label>
                <div class="col-xs-10">
                    <form:input class="form-control" id="dateOfTransfer" path="dateOfTransfer" name="dateOfTransfer"  value="" required="required"/>
                    <form:errors path="dateOfTransfer" cssClass="error" class="notification error" style="display:block"></form:errors>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-offset-2 col-xs-10">
                    <input type="submit" data-icon="check" value="Submit" />
                </div>
            </div>
        </div>
    </div>
</form:form>

<script src="<%= request.getContextPath() %>/WebResources/jquery-1.11.2.min.js"></script>
<script src="<%= request.getContextPath() %>/WebResources/jqueryui/jquery-ui.min.js"></script>
<script src="<%= request.getContextPath() %>/WebResources/bootstrap/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath() %>/WebResources/datetimepicker/jquery.datetimepicker.js"></script>
<script src="<%= request.getContextPath() %>/WebResources/form-validator/jquery.form-validator.min.js"></script>


<script>
    var da = new Date;
    var hours = da.getHours();
    var mins = da.getMinutes();
    if(mins < 15)
    {
        hours--;
        mins = 59;
    }

    var logic = function( currentDateTime ){

        var now = new Date();
        var start = new Date(now.getFullYear(), 0, 0);
        var diff = now - start;
        var oneDay = 1000 * 60 * 60 * 24;
        var day = Math.floor(diff / oneDay);

        // 'this' is jquery object datetimepicker
        if( currentDateTime.getDayOfYear()==day ){

            this.setOptions({
                minTime: hours+':'+mins
            });
        }else{
            this.setOptions({
                minTime: '0:00'
            });
        }
    };

    var d = new Date;
    var dformat = d.getDate().padLeft() + "-" +  (d.getMonth()+1).padLeft() +
            "-" + d.getFullYear() + ' ' +  d.getHours().padLeft() + ":" + d.getMinutes().padLeft()

    jQuery(document).ready(function () {
        jQuery('#dateOfTransfer').datetimepicker({

            value:dformat,
            format:'d-m-Y H:i',
            minDate:'-1970/01/01',//yesterday is minimum date(for today use 0 or -1970/01/01),
            step: 15,
            inline: true,
            onShow:logic,
            defaultSelect:false,
            onChangeDateTime:logic,
            minTime: hours+":"+mins

        });


    });
</script>