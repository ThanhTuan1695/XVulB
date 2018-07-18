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
		    <c:when test="${posts == null}">
		        <div class="alert alert-danger" role="alert">
  					Post is not found 
		  		</div>	
		    </c:when>    
		    <c:otherwise>

					<c:forEach items="${posts}" var="post">
		       <div class="blog-post">
		    		 <h2 class="blog-post-title"><c:out value="#${post.id} - ${post.title}"/></h2>
		    		 <p class="blog-post-meta"><c:out value="${post.createdDay}"/> by <a href="#"><c:out value="${post.createdBy}"/></a></p>
		    		 <img src="img/post-${post.id}.jpg" alt="" style="width: 100%;"/>
		    	   <div style="text-align:  justify;">
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
					</c:forEach>
	

		 </c:otherwise>
		</c:choose>
        </div><!-- /.blog-main -->

        <aside class="col-md-4 blog-sidebar">
          <div class="p-3 mb-3 bg-light rounded" style="text-align:  justify;">
            <h4 class="font-italic">About us</h4>
            <p class="mb-0">We work in IT Security Department of mgm Technology Partners. If you have any questions, please contact us:</p>
			<ul>
					<li>Dennis.Stoetzel@mgm-sp.com-<em> Managing Principal IT Security Department</em> </li>
			</ul>
		</div>

          <div class="p-3">
            <h4 class="font-italic">Archives</h4>
            <ol class="list-unstyled mb-0">
              <li><a href="#">April 2018</a></li>
              <li><a href="#">March 2018</a></li>
            </ol>
          </div>
        </aside><!-- /.blog-sidebar -->

      </div><!-- /.row -->

    </main><!-- /.container -->
	
</t:wrapper>