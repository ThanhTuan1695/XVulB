<%@ page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
	<main role="main" class="container">
      <div class="row">
        <div class="col-md-8 blog-main">
          <h3 class="pb-3 mb-4 font-italic border-bottom">
            Welcome to XVulB
          </h3>
          
          
		<c:choose>
		    <c:when test="${post == null}">
		        <div class="alert alert-danger" role="alert">
  					Post is not found 
		  		</div>	
		    </c:when>    
		    <c:otherwise>

		    
		       <div class="blog-post">
		    		 <h2 class="blog-post-title"><c:out value="${post.title}"/></h2>
		    		 <p class="blog-post-meta"><c:out value="${post.createdDay}"/> by <a href="#"><c:out value="${post.createdBy}"/></a></p>
		    		 <img src="img/post-${post.id}.jpg" alt="" style="width: 100%;"/>
		    	   <div>
		         ${post.content}
		       </div>
		       <div class="row justify-content-start">
		       <section class="comments col-md-10 ">
		       
		       <div class="r"></div>

			   <h5 class="mb-0">
				   Leave your comment
			   </h5>

		       <div class="card-body">
			       <form action="post?id=${post.id}" method="post">
			       <div class="form-group">
				   <label for="author">Author</label>
			       <input class="form-control" type="text" name="author" id="author" placeholder="Author">
			       </div>
			       <div class="form-group">
				   <label for="comment">Comment</label>
			       <textarea class="form-control" name="comment" id="comment" rows="2"></textarea>
			       </div>
			       <button type="submit" class="btn btn-primary">Submit</button>
			       </form>
			   </div>
			   <br>
			   <c:forEach items="${comments}" var="comment">
			       <div class="comments-block">
			    		 <p class="blog-comment-meta"><c:out value="${comment.createdAt}"/> by <strong><c:out value="${comment.createdBy}"/></strong></p>
				    	   <div>
				    	   		<c:choose>
							    <c:when test="${xssPrevention != null}">
							        <c:out value="${comment.comment}"/> 
							    </c:when>    
							    <c:otherwise>
							        ${comment.comment}
							    </c:otherwise>
							</c:choose>
				       </div>
			       </div>
		       </c:forEach>

		       </section>
		       </div>
		  </div>
	

		 </c:otherwise>
		</c:choose>
        </div><!-- /.blog-main -->

        <aside class="col-md-4 blog-sidebar">
          <div class="p-3 mb-3 bg-light rounded">
            <h4 class="font-italic">About</h4>
            <p class="mb-0">Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
          </div>

          <div class="p-3">
            <h4 class="font-italic">Archives</h4>
            <ol class="list-unstyled mb-0">
              <li><a href="#">March 2014</a></li>
              <li><a href="#">February 2014</a></li>
              <li><a href="#">January 2014</a></li>
            </ol>
          </div>
        </aside><!-- /.blog-sidebar -->

      </div><!-- /.row -->

    </main><!-- /.container -->
	
</t:wrapper>