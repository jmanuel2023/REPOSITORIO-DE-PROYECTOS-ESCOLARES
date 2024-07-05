const formulario=document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');

const expresiones = {
    nombreA: /^[a-zA-Z]{1,30}$/, //Letras y espacios, pueden llevar acentos.
    dieta: /^[a-zA-Z]{1,100}$/,
    esperanzaV: /^[0-9]{3}$/,
    añoEx: /^[1[0-9][0-9][0-9]|2[0][0-1][0-9]]$/,
    vivos: /^[0-9]{10}$/,

}

const campos = {
	nombreA: false,
    dieta: false,
    esperanzaV: false,
    añoEx: false,
    vivos: false
}  

const validarFormulario = (e) => {
	switch (e.target.name) {
		case "nombreA":
			validarCampo(expresiones.nombreA, e.target, 'nombreA');
		break;
		case "dieta":
			validarCampo(expresiones.dieta, e.target, 'dieta');
		break;
		case "esperanzaV":
			validarCampo(expresiones.esperanzaV, e.target, 'esperanzaV');
		break;
		case "añoEx":
			validarCampo(expresiones.añoEx, e.target, 'añoEx');
		break;
		case "vivos":
			validarCampo(expresiones.vivos, e.target, 'vivos');
		break;
		
	}
}

const validarCampo = (expresion, input, campo) => {
	if(expresion.test(input.value)){
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos[campo] = true;
	} else {
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos[campo] = false;
	}
}
const validarPassconf = () => {
	const inputPassword1 = document.getElementById('pass');
	const inputPassword2 = document.getElementById('passconf');

	if(inputPassword1.value !== inputPassword2.value){
		document.getElementById(`grupo__passconf`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__passconf`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__passconf .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos['pass'] = false;
	} else {
		document.getElementById(`grupo__passconf`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__passconf`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__passconf .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos['pass'] = true;
	}
}



inputs.forEach((input) => {
	input.addEventListener('keyup', validarFormulario);
	input.addEventListener('blur', validarFormulario);
});

