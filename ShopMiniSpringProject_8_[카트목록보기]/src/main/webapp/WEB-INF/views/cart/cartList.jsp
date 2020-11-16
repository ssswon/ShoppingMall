<%@page import="com.dto.CartDTO"%>
<%@page import="com.dto.GoodsDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    	 //수정버튼
        $(".updateBtn").on("click",function(){
        	var num= $(this).attr("data-num");//번호가져오기 ${dto.num}
        	var gAmount = $("#cartAmount"+num).val();
        	var gPrice= $(this).attr("data-price");
        	console.log("updateBtn")
        	$.ajax({
        		url:"loginCheck/cartUpdate",
        		type:"get",
        		dataType:"text",
        		data:{
        			num:num,
        			gAmount:gAmount
        		},
        		success:function(data,status,xhr){//서버에서 받아와서 처리하지 않음 그냥 value값을 곱하면 됨
        			var sum=parseInt(gAmount)*parseInt(gPrice);
        			$("#sum"+num).text(sum);
        		},
        		error:function(xhr,status,error){
        			
        		}
        	});//end ajaxgkgk
        });
    	 
        //삭제버튼
        $(".delBtn").on("click",function(){
        	var num= $(this).attr("data-xxx");
        	location.href="CartDelServlet?num="+num;
        });
 
        //전체선택
        $("#allCheck").on("click",function(){
        	var result = this.checked;
        	$(".check").each(function(idx,data){
        		this.checked=result;
        	});
        });
        
        //전체cart 삭제
        $("#delAllCart").on("click",function(){
        	var num=[];
        	$("input:checkbox[name='check']:checked").each(function(idx,ele){
        		num[idx]=$(this).val();
        	});
        	console.log(num);
        	location.href="CartDelAllServlet?data="+num;
        });
      //전체cart 삭제
        $("#delAllCart2").on("click",function(){
        	
        	$("form").attr("action", "CartDelAllServlet2");
        	$("form").submit();// trigger
        });
      
        //주문버튼
        $(".orderBtn").on("click",function(){
        	var num= $(this).attr("data-xxx");
        	location.href="CartOrderConfirmServlet?num="+num;
        });
   });
</script>    
<table width="90%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td height="30">
	</tr>

	<tr>
		<td colspan="5" class="td_default">&nbsp;&nbsp;&nbsp; <font
			size="5"><b>- 장바구니-</b></font> &nbsp;
		</td>
	</tr>

	<tr>
		<td height="15">
	</tr>

	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>

	<tr>
		<td height="7">
	</tr>

	<tr>
		<td class="td_default" align="center">
		<input type="checkbox" name="allCheck" id="allCheck"> <strong>전체선택</strong></td>
		<td class="td_default" align="center"><strong>주문번호</strong></td>
		<td class="td_default" align="center" colspan="2"><strong>상품정보</strong></td>
		<td class="td_default" align="center"><strong>판매가</strong></td>
		<td class="td_default" align="center" colspan="2"><strong>수량</strong></td>
		<td class="td_default" align="center"><strong>합계</strong></td>
		<td></td>
	</tr>

	<tr>
		<td height="7">
	</tr>
	
	
	
	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>



	<form name="myForm">
	
		<c:forEach var="dto" items="${cartList }" varStatus="status">
	
	
		<tr>
			<td class="td_default" width="80">
			<!-- checkbox는 체크된 값만 서블릿으로 넘어간다. 따라서 value에 삭제할 num값을 설정한다. -->
			<input type="checkbox"
				name="check" id="check81" class="check" value="${dto.num }"></td>
			<td class="td_default" width="80">${dto.num }</td>
			<td class="td_default" width="80"><img
				src="images/items/${dto.gImage }.gif" border="0" align="center"
				width="80" /></td>
			<td class="td_default" width="300" style='padding-left: 30px'>
			${dto.gName }
				<br> <font size="2" color="#665b5f">[옵션 : 사이즈(${dto.gSize })
					, 색상(${dto.gColor })]
			</font></td>
			<td class="td_default" align="center" width="110">
			${dto.gPrice }
			</td>
			<td class="td_default" align="center" width="90">
			<input class="input_default" type="text" name="cartAmount"
				id="cartAmount${dto.num }" style="text-align: right" maxlength="3"
				size="2" value="${dto.gAmount }"></input></td>
			<td><input type="button" value="수정" 
			class="updateBtn"
			data-num="${dto.num }"
			data-price="${dto.gPrice }">
			
			</td>
			<td class="td_default" align="center" width="80"
				style='padding-left: 5px'><span id="sum${dto.num }">
				${dto.gPrice*dto.gAmount}
				</span></td>
			<td><input type="button" value="주문" class="orderBtn" data-xxx="${dto.num }"></td>
			<td class="td_default" align="center" width="30"
				style='padding-left: 10px'>
				<input type="button" value="삭제" id="xx${dto.num }"
				 class="delBtn" data-xxx="${dto.num }"></td>
			<td height="10"></td>
		</tr>

</c:forEach>

	</form>
	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>
	<tr>
		<td height="30">
	</tr>

	<tr>
		<td align="center" colspan="5"><a class="a_black"
			href="javascript:orderAllConfirm(myForm)"> 전체 주문하기 </a>&nbsp;&nbsp;&nbsp;&nbsp; 
			<a class="a_black" href="#" id="delAllCart"> 전체 삭제하기 </a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="a_black" href="#" id="delAllCart2"> 전체 삭제하기2 </a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="a_black" href="main"> 계속 쇼핑하기 </a>&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<td height="20">
	</tr>

</table>
    