package com.example.disignmode.daili;

/**
 * description 代理对象持有被代理的对象
 * 2019-05-20
 * linghailong
 */
public class BankWorker implements IBank {
    private IBank iBank;
    /**
     *
     * @param bank 持有被代理的对象
     */
    public BankWorker(IBank bank){
        this.iBank=bank;
    }

    /**
     * 被代理的对象在方法中进行逻辑操作
     */
    @Override
    public void applyBank() {
        System.out.println("开始受理");
        iBank.applyBank();
        System.out.println("受理结束");
    }
}
