$(function () {

    var TallerAvanzada = {};

    (function (app) {

        var urlApiRestPaginado = "http://localhost:9001/api/v2/persona";

        app.init = function () {

            /*
             * 
             * NOTA: para la carga inicial de la tabla, comenté la llamada al método app.buscarPersonas();
             * ya que para los fines prácticos resultaba mas prolijo la simulación de un click sobre cualquier opción búsqueda 
             * definida por nosotros.
             */

//            app.buscarPersonas();
            app.oyentes();

            /*La carga de datos inicial, puede ser mediante cualquiera de los métodos definidos,
             * ya que ambos devuelven (en caso que el filtro este vacio ) la totalidad de los datos
             * */
            $("#buscarxApellido").trigger("click");
            //or 
//              $("#buscarxDni").trigger("click");
        };

        app.oyentes = function () {

            // Oyente para cuando se hace click en el boton Eliminar
            $("#cuerpoTabla").on('click', '.eliminar', function () {
                app.eliminarPersona($(this).attr("data-id_persona"));
            });


            $("#buscarxDni").on('click', function (e) {

                var texto = $("#nroDni").val();
                
                /* con este condicional puedo detectar si la ejecución del metodo fue
                 * a travéz de un click fisico(mouse) o fue mediante la sentencia .trigger(click)
                 * */
                if (e.which) {
                    $("#currentpage").val(0);
                    /*reset del numero de pagina cada vez que se ejecuta una búsqueda con un click físico*/
                } 
                

                if (texto !== "") {
                    app.buscarPersonaPorDni(texto);
                } else {
                    app.buscarPersonas();
                }
            });



            $("#buscarxApellido").on('click', function (e) {
                
                
                /* con este condicional puedo detectar si la ejecución del metodo fue
                 * a travéz de un click fisico(mouse) o fue mediante la sentencia .trigger(click)
                 * */
                if (e.which) {
                    $("#currentpage").val(0);
                    /*reset del numero de pagina cada vez que se ejecuta una búsqueda con un click físico*/
                } 
    
                var texto = $("#apellido").val();

                if (texto !== "") {
                    app.buscarPersonaPorApellido(texto);
                } else {
                    app.buscarPersonas();
                }
            });

            /*Listener para actualizar el pie de paginación*/
//            $("#currentSize").change(function () {
//                var current_method = $("#method ").val();
//                $("#currentpage").val(0);
//                $(current_method).trigger("click");
//            });
            
//            $(".dropdown-menu li a").click(function(){
//                var selText = $(this).text();
//                $(this).parents('.btn-group').find('.dropdown-toggle').html(selText);
//                
//                  $("#currentSize").val(selText);
//                var current_method = $("#method ").val();
//                $("#currentpage").val(0);
//                $(current_method).trigger("click");                
//                
//              });

        };

        app.buscarPersonas = function () {

            var url = "http://localhost:9001/api/v1/persona/";

            $.ajax({
                url: url,
                method: 'GET',
                dataType: 'json',
                success: function (datosRecibidos) {
                    datoConvertido = JSON.stringify(datosRecibidos);
                    app.rellenarTabla(datoConvertido);
                },
                error: function () {
                    alert('error');
                }

            });
        };

        app.rellenarTabla = function (datoConvertido) {
            datoParseado = JSON.parse(datoConvertido);
            var linea = "";

            $.each(datoParseado, function (clave, persona) {
                linea += '<tr>' +
                        '<td>' + persona.nombre + '</td>' +
                        '<td>' + persona.apellido + '</td>' +
                        '<td>' + persona.dni + '</td>' +
                        '<td>' +
                        '<a class="pull-left editar" data-id_persona="' + persona.id + '"><span class="glyphicon glyphicon-pencil"></span>Editar</a>' +
                        '<a class="pull-right eliminar" data-id_persona="' + persona.id + '"><span class="glyphicon glyphicon-remove"></span>Eliminar</a>' +
                        '</td>' +
                        '</tr>';
            });

            $("#cuerpoTabla").html(linea);
        };

        // Se elimina un registro de la base de datos
        app.eliminarPersona = function (id) {
            alert(id);
            // Se confirma que se desee eliminar ese registro
            if (confirm("¿Esta seguro que desea eliminar ese registro?")) {
                var url = 'http://localhost:9001/api/v1/persona/' + id;

                $.ajax({
                    url: url,
                    method: "DELETE",
                    dataType: 'json',
                    success: function (datosRecibidos) {
                        datoConvertido = JSON.stringify(datosRecibidos);
                        alert("datosRecibidos");
                        alert('El registro se elimino exitosamente');
                        // app.actualizarDataTable(tipo);
                        app.borrarFila(id);
                    },
                    error: function (datosRecibidos) {
                        alert('Hubo un error al eliminar el registro');
                    }
                });
            }
        };

        app.borrarFila = function (id) { // funcion para borrar un fila

            alert("entre borrar fila");
            var fila = $("#cuerpoTabla")
                    .find("a[data-id_persona='" + id + "']").parent().parent()
                    .remove();
        };

        app.buscarPersonaPorDni = function (dni) {

            var current_method = "/findBy/dni/";
            $("#method ").val("#buscarxDni");

            var current_page = $("#currentpage").val();
            var current_page_size = $("#currentSize").val();

            var url = urlApiRestPaginado + current_method + dni + "?page=" + current_page + "&size=" + current_page_size;

            $.ajax({
                url: url,
                method: 'GET',
                dataType: 'json',
                success: function (datosRecibidos) {

                    app.paginacion(datosRecibidos.totalPages, datosRecibidos.pageable.pageNumber, datosRecibidos.pageable.pageSize, url);

                    console.log(datosRecibidos);
                    datoConvertido = JSON.stringify(datosRecibidos.content);
                    console.log(datoConvertido);
                    app.rellenarTabla(datoConvertido);


                },
                error: function () {
                    alert('error');
                }

            });
        };


        app.buscarPersonaPorApellido = function (apellido) {

            var current_method = "/findBy/apellido/";
            $("#method ").val("#buscarxApellido");

            var current_page = $("#currentpage").val();
            var current_page_size = $("#currentSize :selected").text();

            var url = urlApiRestPaginado + current_method + apellido + "?page=" + current_page + "&size=" + current_page_size;
            
            $.ajax({
                url: url,
                method: 'GET',
                dataType: 'json',
                success: function (datosRecibidos) {

                    app.paginacion(datosRecibidos.totalPages, datosRecibidos.pageable.pageNumber, datosRecibidos.pageable.pageSize, url);

                    console.log(datosRecibidos);
                    datoConvertido = JSON.stringify(datosRecibidos.content);
                    console.log(datoConvertido);
                    app.rellenarTabla(datoConvertido);
                },
                error: function () {
                    alert('error');
                }

            });
        };


        app.paginacion = function (totalpaginas, current, pageSize, busqueda) {

            /*Elimino el contenido html del elemento html con la clase "piepaginacion" */
            $(".piepaginacion").html("");

            /*obtengo el la pagina actual y el nombre botón de búsqueda que fue presionado por última vez.*/
            var current_page = $("#currentpage").val();
            var current_method = $("#method ").val();
          
          
            if (current_page > totalpaginas) {
                current_page = 0;
                $("#currentpage").val(current_page);
                
                alert("");
            }

            /*Dibujamos el pie de la paginacíon*/
            for (var n = 0; n < totalpaginas; ++n) {

                var li = "";

                if (n == current_page) {
                    li = '<li class="page-item active">';
                } else {
                    li = '<li class="page-item">';
                }

                li += '<a class="page-link" href="#" text="' + n + '" >';
                li += '' + n;

                if (n == current_page) {
                    li += '<span  class="sr-only">(current)</span>';
                } else {
                    li += '<span  ></span>';
                }

                li += '</a><li>';

                $(".piepaginacion").append(li);


            }

            $('.page-link').click(function () {
                $("#currentpage").val($(this).attr("text"));
                $(current_method).trigger("click");
            });

            $("#pageSizeDiv").show();
        };

        app.init();

    })(TallerAvanzada);

});
