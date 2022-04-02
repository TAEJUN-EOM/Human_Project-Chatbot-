package lab.spring.tx.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lab.spring.tx.model.Item;
import lab.spring.tx.model.OrderInfo;
import lab.spring.tx.model.Payment;

@Repository("dao") 
public class OrderDAO {
	@Autowired
	SqlSession sqlSession ;	//SqlSessionTemplate¿Ã ¡÷¿‘µ 
	
	public Item getItem(int item_id) {
		Item vo = null;
		vo = sqlSession.selectOne("lab.tx.OrderMapper.getItem", item_id);
		return vo;
	}
	
	public int  addPay(Payment vo) {
		return sqlSession.insert ("lab.tx.OrderMapper.addPay", vo);
	}
  
	public int addOrderInfo(OrderInfo vo) {
		return sqlSession.insert ("lab.tx.OrderMapper.addOrder", vo);
	}
}






