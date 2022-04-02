$(document).ready(function(){
	var navData = `
	<nav class="navbar navbar-expand-lg">
		  <a href="{{url_for('auth.dashboard')}}"><img src="{{url_for('static',filename='/assets/logo.png')}}" width="140" height="50" style="margin-right: 2em;"></a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarText">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item active">
		        <a class="nav-link" href="{{url_for('users')}}">Usuarios</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="{{url_for('protocols')}}">Protocolos</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="{{url_for('monitor')}}">Monitorear</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="{{url_for('settings')}}">Ajustes</a>
		      </li>
		    </ul>
		    <span class="navbar-text">
		      <a href="/auth/logout"><img src="{{url_for('static',filename='/assets/logout.png')}}" width="30" height="30"></a>
		    </span>
		  </div>
	</nav>`;
	$('#navbar').html(navData);
});