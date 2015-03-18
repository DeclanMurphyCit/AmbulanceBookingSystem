<%--
  Created by IntelliJ IDEA.
  User: Declan
  Date: 12/02/2015
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/templating/include.jsp"%>

<link rel="stylesheet" type="text/css"
      href="<% request.getContextPath();%>/resources/jqueryui/jquery-ui.min.css">
<link rel="stylesheet" type="text/css"
      href="<% request.getContextPath();%>/resources/datetimepicker/jquery.datetimepicker.css">

<form:form method="post" class="form-horizontal" id="bookingForm" action="addNewBooking" modelAttribute="ambulancebooking">
    <div id="main-panel" align="center" style="width:500px;">


        <form:errors path="*" cssClass="errorblock" element="div" />

        <div class="form-group">
            <form:label class="control-label col-xs-2" for="patientId" path="patientId">Patient</form:label>
            <div class="col-xs-10">
                <form:select class="form-control" id="patientId" path="patientId" required="required">
                    <form:option value="" label=""/>
                    <c:forEach var="pat" items="${patientList}">
                        <form:option value="${pat.id}" label="${pat.firstName} ${pat.lastName}"/>
                    </c:forEach>
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
</form:form>

<script src="<%= request.getContextPath() %>/resources/jquery-1.11.2.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/jqueryui/jquery-ui.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/datetimepicker/jquery.datetimepicker.js"></script>
<script src="<%= request.getContextPath() %>/resources/form-validator/jquery.form-validator.min.js"></script>


<script>
    Number.prototype.padLeft = function(base,chr){
        var  len = (String(base || 10).length - String(this).length)+1;
        return len > 0? new Array(len).join(chr || '0')+this : this;
    }

    jQuery('#bookingForm').submit(function()
    {
        if (jQuery("#origin").val() == jQuery("#destination").val()) {
            alert("Origin and destinatin must be different locations!")
            return false;
        }

        // ... continue work
    });

    jQuery(document).ready(function () {

        jQuery.validate();

        var d = new Date;
        var dformat = d.getDate().padLeft() + "-" +  (d.getMonth()+1).padLeft() +
                "-" + d.getFullYear() + ' ' +  d.getHours().padLeft() + ":" + d.getMinutes().padLeft()


        jQuery('#dateOfTransfer').val(dformat);

        jQuery('#dateOfTransfer').datetimepicker({
            format:'d-m-Y H:i',
            minDate:'-1970/01/01',//yesterday is minimum date(for today use 0 or -1970/01/01),
            step: 15,
            inline: true
        });

        jQuery("#urgent").change(function() {
            if(this.checked) {
                jQuery("#dateDiv").slideUp(100, function() { jQuery("#dateDiv").hide(); });

            } else {
                jQuery("#dateDiv").slideDown(300, function() { jQuery("#dateDiv").show(); });
            }
        });

        jQuery('#bookingForm').submit(function() {
            if(jQuery("#usrgent").is(':checked'))
                jQuery("#dateOfTransfer").val(dformat);
        });

    });
</script>