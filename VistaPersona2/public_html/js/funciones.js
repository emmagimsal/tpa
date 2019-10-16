$(function () {

    var TallerAvanzada = {};

    (function (app) {

        app.init = function () {

            app.buscarPersonas();
            app.oyentes();
        };

        app.oyentes = function () {

            //Oyente para cuando se hace click en el boton Eliminar
            $("#cuerpoTabla").on('click', '.eliminar', function () {
                app.eliminarPersona($(this).attr("data-id_persona"));
            });

            //Oyente para cuando se hace click en el boton Agregar     
            $("#agregarPersona").on('click', function (event) {  //Evento para el Click del boton agregarPerro
                // bander cero para alta
                $("#id").val(0);
                // el tìtulo que inyecto al modal
                $("#tituloModal").html("Nueva Persona");
                // muestro el modal
                $("#modalPersona").modal({show: true});
            });

            // oyente del botòn guardar
            $("#guardar").on("click", function (event) {
                event.preventDefault();
                // si la bandera es cero vino de Alta
                if ($("#id").val() == 0) {//Si el id es cero entonces se guarda una nueva persona
                    app.guardarRegistro();
                } else {//Si el id es distinto de cero entonces se modifica una persona
                    app.modificarRegistro();
                }
            });

            // OYENTE en el caso de hacer click en editar
            $("#cuerpoTabla").on('click', '.editar', function (event) {   //Evento para el Click en editar Perro
                // tomar el valor del metadato
                $("#id").val($(this).attr("data-id_persona"));
                // tomar en forma idependiente cada valor de la celda navegando en el Dom
                // inyectar en cada atributo del modal , todavìa no se ven
                $("#nombre").val($(this).parent().parent().children().first().html());
                $("#apellido").val($(this).parent().parent().children().first().next().html());
                $("#dni").val($(this).parent().parent().children().first().next().next().html());

                // Inyectar el tìtulo
                $("#tituloModal").html("Editar Persona");
                // ahora muestro el modal con todo los campos cargados para editar
                $("#modalPersona").modal({show: true});
            });


        };


        app.buscarPersonas = function () {

            var url = "http://localhost:9001/api/v1/persona/";

            $.ajax({
                url: url,
                method: 'GET',
                dataType: 'json',
                success: function (datosRecibidos) {
                    datoConvertido = JSON.stringify(datosRecibidos);
                    alert(datoConvertido);
                    app.rellenarTabla(datoConvertido);
                },
                error: function () {
                    alert('error');
                }

            });
        };

        app.rellenarTabla = function (datoConvertido) {
            alert(datoConvertido);
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

        //Se elimina un registro de la base de datos
        app.eliminarPersona = function (id) {
            alert(id);
            //Se confirma que se desee eliminar ese registro
            if (confirm("¿Esta seguro que desea eliminar ese registro?"))
            {
                var url = 'http://localhost:9001/api/v1/persona/' + id;


                $.ajax({
                    url: url,
                    method: "DELETE",
                    dataType: 'json',
                    success: function (datosRecibidos) {
                        datoConvertido = JSON.stringify(datosRecibidos);
                        alert("datosRecibidos");
                        alert('El registro se elimino exitosamente');
//                    app.actualizarDataTable(tipo);
                        app.borrarFila(id);
                    },
                    error: function (datosRecibidos) {
                        alert('Hubo un error al eliminar el registro');
                    }
                });
            }
        };

        app.borrarFila = function (id) {    //funcion para borrar un fila

            alert("entre borrar fila");
            var fila = $("#cuerpoTabla").find("a[data-id_persona='" + id + "']").parent().parent().remove();
        };



// llamada ajax para guardar persona
        app.guardarRegistro = function () {  //funcion para guardar un perro
            var url = 'http://localhost:9001/api/v1/persona/';
            // variable que toma todos los datos del formulario          
            var datosEnviar =
                    {'nombre': $("#nombre").val(),
                     'apellido': $("#apellido").val(),
                        'dni': $("#dni").val()};



            $.ajax({
                url: url,
                method: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(datosEnviar),

                success: function (datosRecibidos) {
                    var datoConvertido = JSON.stringify(datosRecibidos);
                    var datoParseado = JSON.parse(datoConvertido);
                    // esconder el modal 
                    $("#modalPersona").modal('hide');
                    // refrescar la tabla del html agregando el ultimo registro
                    app.actualizarTabla(datoParseado, $("#id").val());
                  app.limpiarModal();
                },
                error: function (datosRecibidos) {
                    alert(datosRecibidos);
                }
            });
        };


        app.modificarRegistro = function () {    //funcion para modificar Perro
            alert("entre en modificar");
            var id = $("#id").val();
            var url = 'http://localhost:9001/api/v1/persona/' + id;

            var datosEnviar =
                    {'nombre': $("#nombre").val(),
                        'apellido': $("#apellido").val(),
                        'dni': $("#dni").val()};



            $.ajax({
                url: url,
                method: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(datosEnviar),
                success: function (datosRecibidos) {

                    $("#modalPersona").modal('hide');
                    // se debe buscar en la tabla el regitro y refrescarla con los datos 
                    app.actualizarTabla(datosRecibidos, $("#id").val());
                   app.limpiarModal();
                },
                error: function (datosRecibidos) {
                    alert("Error en guardar persona");
                    alert(datosRecibidos);
                }
            });
        };



        app.eliminarRegistro = function (id) {   //funcion para eliminar un Perro
            var url = "../../controlador/ruteador/Ruteador.php?accion=eliminar&Formulario=Perro";
            //alert(id);
            var datosEnviar = {id: id};
            alert("Estoy en eliminar Registro");
            $.ajax({
                url: url,
                method: "POST",
                data: datosEnviar,
                success: function (datosRecibidos) {
                    alert('Registro eliminado');
                    // se debe buscar la fila en la tabla y borrar la fila     
                    app.borrar(id);
                },
                error: function (datosRecibidos) {
                    alert('error al eliminar');
                }
            });
        };


        app.actualizarTabla = function (persona, id) {   //funcion para agrgar un perro nuevo o modificar uno existente en la tabla perro
            alert("entre a actualizar");
            if (id == 0) { //ES guardar una persona nueva
                // armo la fila dinamica
                alert("Este es un persona nuevo");
                var linea = '<tr>' +
                        '<td>' + persona.nombre + '</td>' +
                        '<td>' + persona.apellido + '</td>' +
                        '<td>' + persona.dni + '</td>' +
                        '<td>' +
                        '<a class="pull-left editar" data-id_persona="' + persona.id + '"><span class="glyphicon glyphicon-pencil"></span>Editar</a>' +
                        '<a class="pull-right eliminar" data-id_persona="' + persona.id + '"><span class="glyphicon glyphicon-remove"></span>Eliminar</a>' +
                        '</td>' +
                        '</tr>';
                // append agrega al final d ela tabla       
                $("#cuerpoTabla").append(linea);

            } else {    //Es Modificar un perroa existente
                alert("Este es un persona para modificar");
                //busco la fila si vino por modifica
                var fila = $("#cuerpoTabla").find("a[data-id_persona='" + id + "']").parent().parent();

                var linea =
                        '<td>' + persona.nombre + '</td>' +
                        '<td>' + persona.apellido + '</td>' +
                        '<td>' + persona.dni + '</td>' +
                        '<td>' +
                        '<a class="pull-left editar" data-id_persona="' + persona.id + '"><span class="glyphicon glyphicon-pencil"></span>Editar</a>' +
                        '<a class="pull-right eliminar" data-id_persona="' + persona.id + '"><span class="glyphicon glyphicon-remove"></span>Eliminar</a>' +
                        '</td>';
                fila.html(linea);
            }
        };

        //Se vacian los campos del modal
        app.limpiarModal = function () {
            $("#id").val(0);
            $("#nombre").val('');
            $("#apellido").val('');
              $("#dni").val('');
           
        };





        app.init();

    })(TallerAvanzada);


});
