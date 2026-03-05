window.onload = function(){
	document.getElementById("btnInsertar").onclick = () => setActionFormularioPedidos("insertarPedido")
	document.getElementById("btnModificar").onclick = () => setActionFormularioPedidos("modificarPedido")
	document.getElementById("btnBorrar").onclick = () => setActionFormularioPedidos("borrarPedido")
	document.getElementById("btnAceptar").onclick = () => setActionFormularioPedidos("aceptarPedido")
	document.getElementById("btnVaciar").onclick = vaciarFormularioPedidos
	document.getElementById("btnVolver").onclick = volver
	
	document.getElementById("btnInsertarDetalle").onclick = () => setActionFormularioDetalles("insertarDetalle")
	document.getElementById("btnModificarDetalle").onclick = () => setActionFormularioDetalles("modificarDetalle")
	document.getElementById("btnBorrarDetalle").onclick = () => setActionFormularioDetalles("borrarDetalle")
	document.getElementById("btnVaciarDetalle").onclick = vaciarFormularioDetalles
}

function setActionFormularioPedidos(action){
	formularioPedidos.action = action
}

function setActionFormularioDetalles(action){
	formularioDetalles.action = action
}

function volver(){
	document.location = "verListadoPedidos"
}

function vaciarFormularioPedidos(){
	
}

function vaciarFormularioDetalles(){
	
}

