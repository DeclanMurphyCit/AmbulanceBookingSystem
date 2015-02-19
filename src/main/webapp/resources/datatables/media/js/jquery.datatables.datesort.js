jQuery.extend( jQuery.fn.dataTableExt.oSort, {

//    $('#example').dataTable( {
//        columnDefs: [
//            { type: 'datetime-eu', targets: 0 }
//        ]
//    } );

    //    date-time-eu      dd-mm-YYY hh:ii:ss

    "datetime-eu-pre": function ( a ) {
        var x;


        if ( $.trim(a) !== '' ) {
            var frDatea = $.trim(a).split(' ');
            var frTimea = frDatea[1].split(':');
            var frDatea2;

            if(frDatea[0].indexOf('.')>0){
                frDatea2 = frDatea[0].split('.');
            }else if (frDatea[0].indexOf('/') > 0){
                frDatea2 = frDatea[0].split('/');
            } else{
                frDatea2 = frDatea[0].split('-');
            }

//            alert(frDatea[0]+' '+frDatea[1]+' '+frDatea2[2] +' '+ frDatea2[1]+' ' + frDatea2[0]+' ' + frTimea[0]+' ' + frTimea[1]);

            x = (frDatea2[2] + frDatea2[1] + frDatea2[0] + frTimea[0] + frTimea[1]) * 1;
        }
        else {
            x = Infinity;
        }
        return x;
    },


    "datetime-eu-asc": function ( a, b ) {
        return a - b;
    },


    "datetime-eu-desc": function ( a, b ) {
        return b - a;
    },


    //    date-eu      dd/mm/YY[YY]

    "date-eu-pre": function ( date ) {
        date = date.replace(" ", "");
        var eu_date, year;

        if (date == '') {
            return 0;
        }

        if (date.indexOf('.') > 0) {
            /*date a, format dd.mm.(yyyy) ; (year is optional)*/
            eu_date = date.split('.');
        } else if (date.indexOf('/') > 0){
            /*date a, format dd/mm/(yyyy) ; (year is optional)*/
            eu_date = date.split('/');
        } else{
            /*date a, format dd-mm-(yyyy) ; (year is optional)*/
            eu_date = date.split('-');
        }

        /*year (optional)*/
        if (eu_date[2]) {
            year = eu_date[2];
        } else {
            year = 0;
        }

        /*month*/
        var month = eu_date[1];
        if (month.length == 1) {
            month = 0+month;
        }

        /*day*/
        var day = eu_date[0];
        if (day.length == 1) {
            day = 0+day;
        }

        return (year + month + day) * 1;
    },

    "date-eu-asc": function ( a, b ) {
        return ((a < b) ? -1 : ((a > b) ? 1 : 0));
    },

    "date-eu-desc": function ( a, b ) {
        return ((a < b) ? 1 : ((a > b) ? -1 : 0));
    }

} );