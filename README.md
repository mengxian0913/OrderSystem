針對OrderSystem這個程式做test
流程如下:

0.addMembership：如果後面有要用到會員資料的話，需要先新增（還沒做出“非會員”但又有填寫會員的情況）。

1.booking:先選擇訂位，可選擇是否需要留下會員(要的話phone & name ¬¬ "" & "")，但必須選擇位置

2.order:點餐資訊要去Menu那邊查看，用Enum的代號去做添增餐點。        *一次添增一個

3.checkout:把點餐的全部金額加總，並處理打折的部分，最後回傳金額。


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
    
