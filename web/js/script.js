function enviarFormularioOpcion2(){
	const XHR = new XMLHttpRequest();
	  var formData = new URLSearchParams(new FormData(document.getElementById('form'))).toString();
         
	  // Define what happens in case of error
	  XHR.addEventListener('error', (event) => {
	    alert('Oops! Something went wrong.');
	  });

	  // Set up our request
	  XHR.open('POST', 'NewServlet', true);
          XHR.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
          
          XHR.onload = () => {
            if (XHR.readyState === XHR.DONE && XHR.status === 200) {
              console.log("response => " + XHR.response);
              //console.log("response xml=> " + XHR.responseXML);
              document.getElementById('bodyTable').innerHTML=XHR.response;
              mostrarMensaje('Producto agregado exitosamente');
              limpiarFormulario();
            }
          };   
          XHR.send(formData);         
}


function limpiarFormulario(){
    document.getElementById("codigo").value='';
    document.getElementById("nombre").value='';
    document.getElementById("correo").value='';
    document.getElementById("direccion").value='';
}

function mostrarMensaje(mensaje){
  Swal.fire({
    icon: 'success',
    title: mensaje,
    showConfirmButton: false,
    timer: 1500
    })
}

function eliminarProducto(codigo){
    const XHR = new XMLHttpRequest();
    var formData = new URLSearchParams(new FormData());

    // Define what happens in case of error
    XHR.addEventListener('error', (event) => {
      alert('Oops! Something went wrong.');
    });

    // Set up our request
    XHR.open('POST', 'NewServlet', true);
    XHR.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    XHR.onload = () => {
      if (XHR.readyState === XHR.DONE && XHR.status === 200) {
        console.log("response => " + XHR.response);
        mostrarMensaje('Producto Eliminado exitosamente');
        setTimeout( function() { window.location.reload() }, 2000 );
      }
    };        
    formData.append('codigo_producto', codigo);
    formData.append('control', 'ELIMINAR');
    XHR.send(formData); 
    
}

function mostrarProductos(){
    var control = document.getElementById("control");
    control.value="mostrar";
    const XHR = new XMLHttpRequest();
	  var formData = new URLSearchParams(new FormData(document.getElementById('form'))).toString();
	  // Define what happens in case of error
	  XHR.addEventListener('error', (event) => {
	    alert('Oops! Something went wrong.');
	  });
	  // Set up our request
	  XHR.open('POST', 'NewServlet', true);
          XHR.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
          
          XHR.onload = () => {
            if (XHR.readyState === XHR.DONE && XHR.status === 200) {
              console.log("response => " + XHR.response);
              document.getElementById('bodyTable').innerHTML=XHR.response;
              mostrarMensaje('Productos mostrados exitosamente');
              limpiarFormulario();
            }
          };   
          XHR.send(formData); 
}

function limpiarTabla(){
   var tabla=document.getElementById('tabla1');
   var totalFilas = tabla.rows.length;   
   for (var i = totalFilas -1; i >0; i--){
       tabla.deleteRow(i);
   }
}