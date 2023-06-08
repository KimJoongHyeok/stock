package com.example.stock.transaction;

import com.example.stock.service.StockService;

public class TransactionStockService {

    private StockService stockService;

    public TransactionStockService(StockService stockService) {
        this.stockService = stockService;
    }
    /*
    트랜잭션 어노테이션은 아래와 같이 동작하게 되는데 트랙잭션이 끝나는 시점(endTransaction)에 데이터베이스에 update 되기 떄문에
    stockService.decrease 함수는 synchronized 로 다른 쓰레드는 참조하지 못하지만 decrease 가 종료되면 다른 쓰레드에서 접근이 가능하다.
    그러면 갱신되기 전의 데이터를 참조해서 메서드를 수행하기 떄문에 정합성 문제 발생하게 된다.
    그래서 synchronized 를 붙이기 전과 동일한 문제가 발생하게 됨
     */
    public void decrease(Long id, Long quantity) {
        startTransaction();

        stockService.decrease(id, quantity);

        endTransaction();
    }

    public void startTransaction() {

    }

    public void endTransaction() {

    }
}
