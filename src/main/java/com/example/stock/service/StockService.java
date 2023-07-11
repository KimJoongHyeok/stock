package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    // synchronized 는 하나의 프로세스에서만 다른 쓰레드가 접근하지 못하도록 보장, 서버가 여러대일 경우 여러대에서 접근이 가능한 단점이 있음
    // 이럴떄는 Mysql 에서 지원하는 Lock 을 사용해 레이스 컨디션을 조절해야함
    @Transactional(propagation = Propagation.REQUIRES_NEW) // namedLock 시에는 새로운 트랜잭션으로 생성되어야하기 떄문에 추가, 아니라면 삭제
    public synchronized void decrease(Long id, Long quantity) {
        // get Stock
        // 재고감소
        // 저장
        Stock stock = stockRepository.findById(id).orElseThrow();

        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }
}
