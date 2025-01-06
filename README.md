針對OrderSystem這個程式做test
流程如下:

0.addMembership：如果後面有要用到會員資料的話，需要先新增（還沒做出“非會員”但又有填寫會員的情況）-> 就當成有填但跟沒填一樣。

1.booking:先選擇訂位，可選擇是否需要留下會員(要的話phone & name ¬¬ "" & "")，但必須選擇位置

2.order:點餐資訊要去Menu那邊查看，用Enum的代號去做添增餐點。        *一次添增一個

3.checkout:把點餐的全部金額加總，並處理打折的部分，最後回傳金額。

  菜單內容(金額還沒有調，點餐的字串就是此字串(含空格)):

      Pesto Pasta 400$
      Garlic Pasta 400$
      Creamy Pasta 400$
      Tomato Pasta 400$
      
      Beef Steak 500$
      Pork Chop 500$
      Chicken Cutlet 500$
      Lamb Chop 500$
      
      Chocolate Cake 500$
      Burnt Cream 300$
      Mousse Cake 250$
      Brownie 300$
      
      Caesar Salad 300$
      Greek Salad 250$
      House Salad 200$
      
      Black Tea 50$
      Milk Tea 70$
      Orange Juice 150$
      Latte 150$
      Americano 100$
      Cola 100$
      Beer 150$
      
      Fries 150$
      Karaage 150$
      Chicken Nuggets 150$
      Garlic Bread 150$
      Cheese Sticks 150$

  折數:
  
    員工餐:7折
    壽星:8折
    合作校園:85折
    會員滿5000之折價券:9折
    會員滿10000之折價券:85折
  
  服務費:
  
    員工餐:不再額外加收服務費
    假日(週六、日)與晚間:15%服務費
    其餘:10%服務費
  

套餐折價:

  套餐A:
  
    pasta+drink=20
    steak+drink=20
    
  套餐B:
  
    pasta+appetizer=40
    steak+appetizer=40
    
  套餐C:
  
    pasta+salad+drink=50
    steak+salad+drink=50
    
  套餐D:
  
    pasta+appetizer+drink=70
    steak+appetizer+drink=70
    
  套餐E:
  
    pasta+dessert+drink=100
    steak+dessert+drink=100
    
  套餐F:
  
    pasta+dessert+appetizer+salad+drink=250
    steak+dessert+appetizer+salad+drink=250
    
