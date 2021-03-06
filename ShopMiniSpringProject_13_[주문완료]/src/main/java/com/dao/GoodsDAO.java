package com.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.OrderDTO;

@Repository
public class GoodsDAO {

	@Autowired
	SqlSessionTemplate template;
	
	public List<GoodsDTO> goodsList(String gCategory) {
		List<GoodsDTO> list = template.selectList("GoodsMapper.goodsList",gCategory);
		return list;
	}

	public GoodsDTO goodsRetrieve(String gCode) {
		GoodsDTO dto = template.selectOne("GoodsMapper.goodsRetrieve", gCode);
		return dto;
	}

	public void cartAdd(CartDTO dto) {
		int n = template.insert("CartMapper.cartAdd",dto);
		System.out.println("GoodsDAO : cartAdd() : insert수 ="+n);
	}

	public List<CartDTO> cartList(String userid) {
		List<CartDTO> list = template.selectList("CartMapper.cartList", userid);
		return list;
	}

	

	public void cartUpdate(HashMap<String, String> map) {
		template.update("CartMapper.cartUpdate",map);
	}

	public CartDTO cartByNum(int num) {
		CartDTO dto = template.selectOne("CartMapper.cartByNum", num);
		return dto;
	}

	public void cartDelete(int num) {
		template.delete("CartMapper.cartDelete",num);
		
	}

	public void delAllCart(List<Integer> num) {
		template.delete("CartMapper.cartAllDel", num);
	}

	public CartDTO orderConfrimByNum(int num) {
		CartDTO cdto = template.selectOne("CartMapper.orderConfirmByNum", num);
		return cdto;
	}
	
	@Transactional
	public void orderDone(OrderDTO dto) {
		template.insert("CartMapper.orderDone",dto);
	}

}
