<%@ page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page import="java.util.List"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
	  <main role="main" class="container">
      <div class="row">
        <div class="col-md-8 blog-main">
          <h3 class="pb-3 mb-4 font-italic border-bottom">
            Welcome to XVulB
          </h3>
		  <c:if test="${searchquery != null}">
		    <div class="alert alert-info" role="alert">
		    		Searching result for <strong>
		    			<c:choose>
					    <c:when test="${xssPrevention != null}">
					        <c:out value="${searchquery}"/>
					    </c:when>    
					    <c:otherwise>
					        ${searchquery}
					    </c:otherwise>
					</c:choose>
		  		</strong> 
			</div>	
		  </c:if>   
		  <c:forEach items="${posts}" var="post">
		    <div class="blog-post">
		      <h2 class="blog-post-title">
		        <a class="title" href="post?id=${post.id}" >
		          <c:out value="${post.title}"/>
		        </a>
		      </h2>
		      <p class="blog-post-meta"><c:out value="${post.createdDay}"/> by <a href="#"><c:out value="${post.createdBy}"/></a></p>
		      <div style="text-align:  justify;">
		      <c:choose>
    				<c:when test="${fn:length(post.content) gt 201}">
		      		${post.content.substring(0,200)}... <a href="post?id=${post.id}">Read more</a>
		      	</c:when>
		      	<c:otherwise>
		      		${post.content}
		      	</c:otherwise>
		      </c:choose>
		      </div>
		    </div>
          </c:forEach>

          <nav class="blog-pagination">
            <a class="btn btn-outline-primary" href="#">Older</a>
            <a class="btn btn-outline-secondary disabled" href="#">Newer</a>
          </nav>
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