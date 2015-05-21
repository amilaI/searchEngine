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
			<div class="row">
				<div  style="margin-top: 200px" align="center" class="col-md-12">
					<form class="form-search"  action="../User/retrieve" method="get">
					  <input id="search-text" name="search-text" type="text" style="height: 40px ; width: 60%"  class="input-large textLarge search-query">
					  <button type="submit" class="btn" style="background-color: rgb(22, 73, 154) ! important;font-weight:bold; color: white;" ><i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;Search Now</button>
					</form>
				</div><!-- /col-lg-8 -->
			</div><!-- /row -->
			
			<!-- <div  class="row">
				
				<div class="col-md-12">

				

			</div>
			
			</div> -->
			
			<div  class="row">
					<div class="col-md-2">
					</div>
					<div class="col-md-8">
								<div class="page-header">
								  <h2>Always Learning <small>Create Content Now</small></h2>
								  <hr>
								  <div align="center">
								   		<button type="submit" class="btn" data-toggle="modal" data-target="#createContentModal" style="background-color:rgba(22, 154, 103, 1) !important;font-weight:bold; color: white;" ><i class="glyphicon glyphicon-pencil"></i>&nbsp;&nbsp;Create Content</button>
								    	<button type="submit" class="btn" style="background-color:rgba(22, 154, 103, 1) !important;font-weight:bold; color: white;" ><i class="glyphicon glyphicon-wrench"></i>&nbsp;&nbsp;Edit Drafts</button>
								  </div>
								</div>

					</div>
					<div class="col-md-2">
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
	<div class="modal fade" id="createContentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 align="center" class="modal-title" id="myModalLabel"><i class="glyphicon glyphicon-pencil"></i>&nbsp;&nbsp;Sample Content Uploder (only)</h3>
      </div>
      <div class="modal-body">
		       
       			<div class="form-group">
       			<form name="uploader" method="POST" action="../User/upload"	enctype="multipart/form-data"  modelAttribute="uploadedFile">
       			<label for="InputDescrip">Content Description</label>
			    <span class="help-block">The text  you enter below will be displayed in Search result description</span>			    
			    <textarea   name="description" cols="60" rows="5" placeholder="Enter Description ..." required></textarea>
			    <br><br>
				<label for="InputFile">File input</label>
				<p class="help-block">Please upload a pdf,doc,.xls file</p>
				<input type="file" name="file" id="file">
				<br>
				
				<input class="btn" style="background-color:rgba(22, 154, 103, 1) !important;font-weight:bold; color: white;margin-left: 40%" type="submit" value="Upload Content">
				</form>
				</div>
      </div>
    
    </div>
  </div>
</div>
</body>
</html>