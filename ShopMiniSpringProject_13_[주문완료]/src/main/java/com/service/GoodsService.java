package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.GoodsDAO;
import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.OrderDTO;

@Service
public class GoodsService {
	
	@Autowired
	GoodsDAO dao; 
	public List<GoodsDTO> goodsList(String gCategory){
		List<GoodsDTO> list = dao.goodsList(gCategory);
		return list;
	}
	public GoodsDTO goodsRetrieve(String gCode) {
		GoodsDTO dto = dao.goodsRetrieve(gCode);
		return dto;
	}
	public void cartAdd(CartDTO dto) {
		dao.cartAdd(dto);
		
	}
	public List<CartDTO> cartList(String userid) {
		List<CartDTO> list = dao.cartList(userid);
		return list;
	}

	public void cartUpdate(HashMap<String, String> map) {
		dao.cartUpdate(map);
	}
	public CartDTO cartByNum(int num) {
		CartDTO dto = dao.cartByNum(num);
		return dto;
	}
	public void cartDelete(int num) {
		dao.cartDelete(num);
	}
	public void delAllCart(List<Integer> num) {
		dao.delAllCart(num);
	}
	public CartDTO orderConfirmByNum(int num) {
		CartDTO cdto = dao.orderConfrimByNum(num);
		return cdto;
	}
	
	@Transactional	//트렌젝션처리를 하기 위함
	public void orderDone(OrderDTO dto, int orderNum) {
		dao.orderDone(dto);	// 주문정보저장 (insert)
		dao.cartDelete(orderNum);	///카트에서 삭제, 두 처리를 트렌젝션 처리 한다.
		
	}
	
}
