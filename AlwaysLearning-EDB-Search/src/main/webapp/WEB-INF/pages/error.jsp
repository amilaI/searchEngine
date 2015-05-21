<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified Jquery 1.10.2 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>


<link rel="stylesheet" type="text/css" href="https://mycloud.pearson.com/myCloudAssets/css/mycloudmaster.min.css?2.0.19.0">
<style type="text/css">
.footer {
  position: absolute;
  bottom: 0;
  width: 100%;
  
}
</style>

</head>
<body>
	<div role="navigation" class="navbar navbar-fixed-top" style="background-color: black;">
      <div class="container">
       
          <h2  style="text-align: center;color: white">Engineering Dash board - Always Learning Module - Search PoC</h2>
        
        <div class="navbar-collapse collapse">
         
        </div><!--/.navbar-collapse -->
      </div>
    </div>
        
	<div class="container">
			
			
			<div  class="row">
					
					<div class="col-md-12">
								<div class="page-header" style="margin-top: 100px">
								  <h2 style="color: red">Oops! <small >Something went wrong. Exception throwed</small></h2>
								  
							
								</div>
								  <div class="panel panel-danger">
								  		  <div class="panel-heading">
									        <h3 class="panel-title">Exception Summary</h3>
									      </div>
									      <div class="panel-body">
									        <p>${exception}</p>
									      </div>
									      
								  </div>

					</div>
							
			</div>	
			
	    </div> 
	<!-- <div class="footer">
		<img alt="" style="width: 100%" src="http://www.pearson.com/content/dam/pearson-corporate/images/section-images/media/logos/Pearson_WebBar_Bottom_Blue_RGB.jpg">
	</div> -->
	
	<div class="navbar" id="footerArea">
    <div class="navbar-inner">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <a href="/" class="brand" id="footerAreaNavBrand">
                        <img src="https://mycloud.pearson.com/myCloudAssets/img/tagline.png?2.0.19.0" alt="Always Learning">
                    </a>
                </div>
                <div class="col-md-6">
                    <div class="pull-right">
                        <a href="/">
                            <img src="https://mycloud.pearson.com/myCloudAssets/img/pearson.png?2.0.19.0" alt="Pearson">
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
	</div>
	
</body>
</html>