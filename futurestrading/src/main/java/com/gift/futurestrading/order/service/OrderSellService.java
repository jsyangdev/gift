package com.gift.futurestrading.order.service;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gift.futurestrading.order.mapper.OrderSellMapper;
import com.gift.futurestrading.order.vo.OrderSellVo;

/**
 * @author 정진우
 * @ClassName : OrderSellService
 * @Version : JDK1.8
 * @LatestUpdate : 2018-12-28
 * 주문 등록 , 체결 목록 처리
 */

@Service
public class OrderSellService {
	@Autowired
	private OrderSellMapper orderSellMapper;
	
	/**
	 * @author 정진우
	 * addSellerOrder 요청 처리
	 * 데이터베이스 요회후 가능여부 리턴 (0 or 1)
	 * @param : String
	 * @return : int
	 */
	
	public int addSellerOrder(OrderSellVo orderSellVo) {
		System.out.println("sellerService.insertseller() 호출");
		/* PK값 자동 증가 코드 */
		int sellerMaxId = orderSellMapper.selectOneAutoMax();
		System.out.println(sellerMaxId+"sellerMaxId 호출");
		sellerMaxId += 1;
		String orderSellerPkLetter = "order_sell_"+sellerMaxId;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("order_sell_pk",orderSellerPkLetter);
		map.put("fk_seller__order_sell", orderSellVo.getFkSellerOrderSell());
		map.put("fk_item_detail__order_sell", orderSellVo.getFkItemDetailOrderSell());
		map.put("order_sell_seller_name", orderSellVo.getFkSellerOrderSell());
		map.put("order_sell_method", orderSellVo.getOrderSellMethod());
		map.put("order_sell_per_price", orderSellVo.getOrderSellPerPrice());
		map.put("order_sell_amount", orderSellVo.getOrderSellAmount());

		int insertResult = orderSellMapper.insertOrderSeller(map);

		return insertResult;
	}
}
