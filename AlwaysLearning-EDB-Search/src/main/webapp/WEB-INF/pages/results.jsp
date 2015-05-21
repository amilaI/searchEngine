<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Results</title>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

	<!-- Optional theme -->
	<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="https://mycloud.pearson.com/myCloudAssets/css/mycloudmaster.min.css?2.0.19.0">
	
	<style type="text/css">
	.footer {
	  position: relative;
	  bottom: 0;
	  width: 100%;	  
	}
	
	#footerArea {
		position: relative; !important
	}
	</style>
</head>
<body>
	<div role="navigation" class="navbar" style="background-color: black;">
      <div class="container">
       
          <h2  style="text-align: center;color: white">Engineering Dash board - Always Learning Module - Search PoC</h2>
        
        <div class="navbar-collapse collapse">
         
        </div><!--/.navbar-collapse -->
      </div>
    </div>

		

	 	<div class="row">
        	<div class="col-md-12">
        	<div>			       
			     <div id="bs-example-navbar-collapse-2" class="collapse navbar-collapse" >
			          <form role="search" class="navbar-form navbar-left"  action="../User/retrieve" method="get">
			            <div class="form-group">
			              <input type="text" id="search-text" name="search-text" style="width: 600px;" placeholder="Search Content" class="form-control">
			            </div>
			             <button type="submit" class="btn" style="background-color: rgb(22, 73, 154) ! important;font-weight:bold; color: white;" ><i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;Search Now</button>
			          </form>
			     </div>
			  </div>
       		</div>
      	</div>
      	
      	<div class="row">
        	<div class="col-md-12">
        	<h1 style="margin :10px  10px 10px 40px">Search Results</h1>
        	<hr>
        </div>
      	</div>

		<div class="row">
        	<div class="col-md-12">
	        	<ul class="nav nav-pills"  style="margin-left:40px">
				  <li class="active"><a href="#">Articles</a></li>
				  <li><a href="#">Images</a></li>
				  <li><a href="#">Videos</a></li>
				</ul>        	
        	<h5 style="margin :10px  10px 10px 40px">About ${numberOfResults} results (${time} milli seconds) </h5>
        	</div>
      	</div>

      <div class="row">
        <div class="col-md-8">
        		<div style="margin:20px  0 0 40px ;" class="panel panel-default"> 
        			
  					<div class="panel-body">
        			

					<h3>${error}</h3>
					<c:forEach items="${results}" var="result">					   					
					   		<div style="margin-top:20px;padding-top: 2px">	
		        				<h3><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;<a href="${result.id}"><c:out value="${result.title}"/></a></h3> 
		        				<p><c:out value="${result.description}"/></p>
		        				<p style="color: rgb(149, 149, 149);"><span style="font-weight: bold;" class="glyphicon glyphicon-time">&nbsp;Date Created</span>: 2014 - 05 -07   	&nbsp;&nbsp;	<span class="glyphicon glyphicon-user" style="font-weight: bold;">&nbsp;Author</span> - Amila Iddamalgoda   &nbsp;&nbsp;<span class="glyphicon glyphicon-tags">&nbsp;Article,Attachment</span></p>
		        				<hr class="divider">	
		        			</div>
					</c:forEach>
  					
 	       			</div>
        		</div>

        </div>
        <div class="col-md-4"></div>
      </div>
      <div class="row">
      <div class="col-md-8">
		      <ul class="pagination" style="float: right; margin-right: 10px">
		      <li class="disabled"><a href="#">«</a></li>
		      <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
		      <li><a href="#">2</a></li>
		      <li><a href="#">3</a></li>
		      <li><a href="#">4</a></li>
		      <li><a href="#">5</a></li>
		      <li><a href="#">»</a></li>
		      </ul>
      </div>
      <div class="col-md-4"></div>
      </div>
   
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