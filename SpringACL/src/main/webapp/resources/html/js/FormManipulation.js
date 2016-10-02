/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function Maindelete( urlLink,data,divToAppend){


                $.ajax({
                    type: "POST",
                    datatype:"text/html",
                    data:{"id":data},
                    url:urlLink,

                    success: function(msg){


                     location.reload();


               },
                    error : function (jqXHR,status){
                        alert(jqXHR.responseText);
                    }

                });

    


}




function Maindelete( urlLink,data1,data2,param1,param2,divToAppend){


                $.ajax({
                    type: "POST",
                    datatype:"text/html",
                    data:{param1:data1,param2:data2},
                    url:urlLink,

                    success: function(msg){


                     location.reload();


               },
                    error : function (jqXHR,status){
                        alert(jqXHR.responseText);
                    }

                });




}










$(function() {
		// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
		$( "#dialog:ui-dialog" ).dialog( "destroy" );
                $( "#dialog-form" ).dialog({
			autoOpen: false,
			height: 300,
			width: 350,
			modal: true,
			buttons: {
				"Edit Values": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );



				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});






	});












function dataTable(a){


                $("#"+a).dataTable({
                    "bPaginate": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers",
                    "bFilter": true,
                    "bSort": true,
                    "bInfo": true,
                    "sScrollY": "90%",
                    "sScrollX": "90%",

                    "bScrollCollapse": true,
                    "bAutoWidth": false


                });






            }

function MainDataPass( urlLink,data1,divToAppend){

                $.ajax({
                    type: "POST",
                    datatype:"text/html",
                    data:{"id":data1},
                    url:urlLink,

                    success: function(msg){


                   $("#CarrierCurrencyDetail").empty();
                   $("#CarrierCurrencyDetail").append(msg);


               },
                    error : function (jqXHR,status){
                        alert(jqXHR.responseText);
                    }

                });

  


}




 

