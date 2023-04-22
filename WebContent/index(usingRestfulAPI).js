
function performGetRequest(){
  console.log('Launched js');
  
  var resultElement = document.getElementById('toDoListResult');
  var todoId = document.getElementById('login_ID').value;
  resultElement.innerHTML = '';

  axios.get('localhost:8080/ToDoListApp/webresources/todolists/list', {
	    params: {
	        id: todoId
	      }
	    })
  	
    .then(function (response) {
    	console.log('js - succesful');
      resultElement.innerHTML = generateSuccessHTMLOutput(response);
    })
    .catch(function (error) {
    	console.log('js - failed');
      resultElement.innerHTML = generateErrorHTMLOutput(error);
    });
}

function generateSuccessHTMLOutput(response) {
	  return  '<h4>Result:</h4>' +
	          '<h5>Status:</h5>' +
	          '<pre>' + response.status + ' ' + response.statusText + '</pre>' +
	          '<h5>Headers:</h5>' +
	          '<pre>' + JSON.stringify(response.headers, null, '\t') + '</pre>' +
	          '<h5>Data:</h5>' +
	          '<pre>' + JSON.stringify(response.data, null, '\t') + '</pre>';
	}

	function generateErrorHTMLOutput(error) {
	  return  '<h4>Result:</h4>' +
	          '<h5>Message:</h5>' +
	          '<pre>' + error.message + '</pre>' +
	          '<h5>Status:</h5>' +
	          '<pre>' + error.response.status + ' ' + error.response.statusText + '</pre>' +
	          '<h5>Headers:</h5>' +
	          '<pre>' + JSON.stringify(error.response.headers, null, '\t') + '</pre>' +
	          '<h5>Data:</h5>' +
	          '<pre>' + JSON.stringify(error.response.data, null, '\t') + '</pre>';
	}