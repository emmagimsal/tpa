$(function() {

	var TallerAvanzada = {};

	(function(app) {

		app.init = function() {

			app.buscarPersonas();
			app.oyentes();
		};

		app.oyentes = function() {

			// Oyente para cuando se hace click en el boton Eliminar
			$("#cuerpoTabla").on('click', '.eliminar', function() {
				app.eliminarPersona($(this).attr("data-id_persona"));
			});
			
			
			$("#buscarxDni").on('click', function() {
				
				var texto=$("#nroDni").val();
				
				if( texto !="" ){
					app.buscarPersonaPorDni(texto );
					
				}else{
					app.buscarPersonas();
				}
				
				
			});
			
			

			$("#buscarxApellido").on('click', function() {
				var texto=$("#apellido").val();
				
				if( texto !="" ){
					app.buscarPersonaPorApellido(texto);
					
				}else{
					app.buscarPersonas();
				}
				
				
			});

		};

		app.buscarPersonas = function() {

			var url = "http://localhost:9001/api/v1/persona/";

			$.ajax({
				url : url,
				method : 'GET',
				dataType : 'json',
				success : function(datosRecibidos) {
					datoConvertido = JSON.stringify(datosRecibidos);
//					alert(datoConvertido);
					app.rellenarTabla(datoConvertido);
				},
				error : function() {
					alert('error');
				}

			});
		};

		app.rellenarTabla = function(datoConvertido) {
//			alert(datoConvertido);
			datoParseado = JSON.parse(datoConvertido);
			var linea = "";

			$
					.each(
							datoParseado,
							function(clave, persona) {
								linea += '<tr>'
										+ '<td>'
										+ persona.nombre
										+ '</td>'
										+ '<td>'
										+ persona.apellido
										+ '</td>'
										+ '<td>'
										+ persona.dni
										+ '</td>'
										+ '<td>'
										+ '<a class="pull-left editar" data-id_persona="'
										+ persona.id
										+ '"><span class="glyphicon glyphicon-pencil"></span>Editar</a>'
										+ '<a class="pull-right eliminar" data-id_persona="'
										+ persona.id
										+ '"><span class="glyphicon glyphicon-remove"></span>Eliminar</a>'
										+ '</td>' + '</tr>';
							});

			$("#cuerpoTabla").html(linea);
		};

		// Se elimina un registro de la base de datos
		app.eliminarPersona = function(id) {
			alert(id);
			// Se confirma que se desee eliminar ese registro
			if (confirm("¿Esta seguro que desea eliminar ese registro?")) {
				var url = 'http://localhost:9001/api/v1/persona/' + id;

				$.ajax({
					url : url,
					method : "DELETE",
					dataType : 'json',
					success : function(datosRecibidos) {
						datoConvertido = JSON.stringify(datosRecibidos);
						alert("datosRecibidos");
						alert('El registro se elimino exitosamente');
						// app.actualizarDataTable(tipo);
						app.borrarFila(id);
					},
					error : function(datosRecibidos) {
						alert('Hubo un error al eliminar el registro');
					}
				});
			}
		};

		app.borrarFila = function(id) { // funcion para borrar un fila

			alert("entre borrar fila");
			var fila = $("#cuerpoTabla")
					.find("a[data-id_persona='" + id + "']").parent().parent()
					.remove();
		};

		app.buscarPersonaPorDni = function(dni) {

			var url = "http://localhost:9001/api/v2/persona/findBy/dni/" + dni;

			$.ajax({
				url : url,
				method : 'GET',
				dataType : 'json',
				success : function(datosRecibidos) {

					
					app.paginacion( datosRecibidos.totalPages, datosRecibidos.pageable.pageNumber, datosRecibidos.pageable.pageSize, url);
					
					console.log(datosRecibidos);
					datoConvertido = JSON.stringify(datosRecibidos.content);
					console.log(datoConvertido);
					app.rellenarTabla(datoConvertido);
					
								
					
					
				},
				error : function() {
					alert('error');
				}

			});
		};
		
		
		app.buscarPersonaPorApellido = function(apellido) {

			var url = "http://localhost:9001/api/v2/persona/findBy/apellido/" + apellido;

			$.ajax({
				url : url,
				method : 'GET',
				dataType : 'json',
				success : function(datosRecibidos) {

					app.paginacion( datosRecibidos.totalPages, datosRecibidos.pageable.pageNumber, datosRecibidos.pageable.pageSize,url );
					
					console.log(datosRecibidos);
					datoConvertido = JSON.stringify(datosRecibidos.content);
					console.log(datoConvertido);
					app.rellenarTabla(datoConvertido);
				},
				error : function() {
					alert('error');
				}

			});
		};

		
		app.paginacion = function(totalpaginas, current, pageSize,busqueda) {
			
			$(".piepaginacion").html("");
		
			for (var n = 0; n < totalpaginas; ++ n){
				
				var li='<li class="page-item active">';
				li+='<a class="page-link" href="#" text="'+n+'" >';
				li+=''+n;
				li+='<span  class="sr-only">(current)</span>';
				li+='</a><li>';
				
				$(".piepaginacion").append(li);
				
//				li+='<a class="page-link" href="http://localhost/VistaPersona1/public_html/'+busqueda+'?page='+n+'&size=20" text="'+n+'" >';
				
			}
		
		};
		
		app.init();

	})(TallerAvanzada);

});
