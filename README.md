## 物件導向程式分析與實作
主題 : UML Editor

## 功能性需求
- [x] class 與 use case 等物件可以拖曳
- [x] line 等物件可以拖曳到其他物件上
- [x] 修改物件中的名字
- [x] 可以對物件進行 Group 與 Ungroup
- [x] 在select mode 時滑鼠按住會隨座標產生一長方形，供選取物件用

## 使用技術
1. Java
2. Design Pattern
  - Singleton
  - Factory
3. 以 OOP 架構進行 design

## UI介面
  1. **基本畫布**
![image](https://github.com/Y1YangLin/UML-Editor/assets/83540570/4906dc9d-1c72-4d31-839b-1c658aaa7583)

  2. **class object 與 use case object**
![image](https://github.com/Y1YangLin/UML-Editor/assets/83540570/fbfa5e65-3804-4171-95f6-2501ef4f2d28)

  3. **Association Line 等各式線條**

![image](https://github.com/Y1YangLin/UML-Editor/assets/83540570/c46a6a80-cde5-44df-9a9f-a5f94c3e2d41)

  4. **Group 物件**


![image](https://github.com/Y1YangLin/UML-Editor/assets/83540570/1c23dd26-ee85-4cd9-a94b-7aa56b973fe5)

  5. **在select mode 拖曳滑鼠產生長方形選取物件**

![image](https://github.com/Y1YangLin/UML-Editor/assets/83540570/0cb318fc-e221-4edc-9113-5b5735d26056)

  6. **選擇物件時按照物件深度，選擇最上面之物件**

  被點擊到之物件會顯示其 4 個可供連線之 port
![image](https://github.com/Y1YangLin/UML-Editor/assets/83540570/0f17c227-afae-4a5d-a570-1cf09cfadc39)
