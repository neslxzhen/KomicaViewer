### 框架
MVVM in Android

### Min Version
 - < 7.0.0 (N, api=24)，getReplies()無法日期排序
 - < 4.4 (KITKAT, api=19)，Post.hashCode()無法比對重複

### 特色
 - 為了符合Komica聯合站的獨特架構，將`Host`與`Board`分離，兩者之間的Url並沒有依賴性，僅依靠`ProjectUtils.getPostModel()`來取得依存關係。

### 分支說明
 - 強化MVVM的觀念，增強Model的功能
 - 更好的commit格式

### 學習重點
 - MVVM
 - fragment, **Navigation-fragment**, AppBar新功能實戰
 - 遵守SOLID
 - POJO

### 常用功能
 - [x] 預覽reply的引用內容(ex. >>12345678 (我的家庭真可愛...) )
 - [x] 點擊開啟樹狀留言圖Dialog
 - [x] 閱讀歷史
 - [ ] 偵測連結開啟App
 - [ ] Po文
 - [ ] 點擊留言
 - [ ] 備份
 - [ ] 收藏貼文
 - [x] Open Url in Browser
 - [ ] 肉餅臉產生器
 - [ ] 依據ID生出頭像

### 目前困難
 - 像Pitt的點擊開圖
 - GIF裏站等站的原圖被js加密過
 
###### 未來展望
 - 為使用者推薦K島上常用圖片(搜尋、上傳)
 - 追蹤文章
 - 個人化推薦文章(自行設定關鍵字)
 
### 目前版面
###### Komica Top50:
   - [x] komica.org
      - sora: [綜合,男性角色,短片2,寫真],[改造,求圖]
      - 2cat: 
         - [新番捏他,新番實況,漫畫,動畫,萌,車]
         - [2CAT,短片,賽車遊戲,返信娘,PSV/手機壁,Pixmicat!,詢問,生存遊戲,大自然,(Web),貓管理部,戰略遊戲]
      - tomo: [四格]
      - luna: [女性角色,歡樂惡搞,GIF,Vtuber],[宣傳]
      - aqua: 
         - [蘿蔔,鋼普拉,影視,特攝,軍武,中性角色,遊戲速報,飲食,小說,遊戲王,奇幻/科幻,電腦/消費電子,塗鴉王國,新聞,布袋戲,紙牌,網路遊戲]
         - [氣象,模型,玩偶,數學,校園,歐美動畫,夢,MAD,動物朋友,遊戲王,性轉換,3D,同人,體育,程設交流,掛圖,文化交流,擬人化,音樂,綜合討論,豆知識,連結,電腦,單身,寫作,精華區,少女漫畫,高解析度,FLASH]
   - [ ] vi.anacel.com [Figure/GK]
   - [x] acgspace.wsfun.com [艦隊收藏]
   - [x] komica.dbfoxtw.me 
      - [人外]
      - [獨立遊戲,遊戲設計,Homestuck]
   - [x] anzuchang.com [Idolmaster]
   - [x] komica.yucie.net
      - [格鬥遊戲]
      - [投票所,交易合購,彈幕,養成遊戲,FEZ,Pretty Cure,京都動畫,聲優綜合,田村/堀江/水樹,魔女,妹系,Lolita Fashion,足球,犬,貓]
   - [x] kagaminerin.org 
      - [3D STG,動作遊戲]
      - [閒談@香港,星座命理,美容,2D STG,冒險遊戲,RPG,APH,Pokemon,GAINAX,角色配對,催淚,素食]
   - [ ] p.komica.acg.club.tw [兄貴],[正太]
   - [x] 2cat.org 
      - [碧藍幻想,手機遊戲,Azur Lane,網頁遊戲]
      - [動物綜合,二次壁,職業,財經,法律,繪師,猜謎,天文,超常現象,服飾,女性向遊戲,VR/體感遊戲,桌上遊戲]
   - [x] mymoe.moe (關站，Code白寫了QQ) 
      - [PAD,綜合2,三次實況]
      - [歷史,笑話,New Age,政治,耳機,髮型,家政,讀書筆記,RPG Maker,CosmicBreak,Elsword,DNF,DOTA2,GW2,LOL,PSO2,SDGO,白貓Project,流亡黯道 PoE,新瑪奇英雄傳,爐石戰記,LoveLive!,禁書,STEAM]
   - [x] strange-komica.com [魔物獵人]
   - [ ] zawarudo.org 
      - [少女前線,AGA]
      - [COSPLAY,AGA,少女前線,線上繪圖,MMD/Vocaloid]
   - [x] gzone-anime.info 
      - [TYPE-MOON]
      - [綜合學術,生活消費,藝術,圖書]

###### Komica All 與 Top50 的差集:
   - [ ] moecorner.com [Apple]
   - [ ] 2nee.org [三次壁]
   - [ ] komicolle.org [Komicolle]
   - [ ] rthost.fam.cx [AA、UP]
   - [ ] rths.ml [祭典]
   - [ ] fenrisulfr.org [],[GTA,戰車世界,戰地風雲,戰爭雷霆,戰機世界,戰艦世界]
   - [ ] eclair.nagatoyuki.org [],[海外,流言終結,煩惱相談,安價,涼宮,酒]
   - [ ] boguspix.com [],[故事接龍,奈葉,御姊,機娘]
   - [ ] moecorner.com/iboards [],[戀愛,麻將,SQEX]
   - [ ] tsukisiro.snow-sugar.net [],[治癒系,鐵道]
   - [ ] kemono.wtako.net [],[塗鴉保育區,獸]
   - [ ] komica.blogspot.com (站務公告)
   - [ ] alica.dreamhosters.com [],[螢幕攝,巫女,燃,葉鍵,Maid]
   - [ ] www.karlsland.net [],[Strike Witches,KOEI]
   - [ ] travel.voidfactory.com [],[旅遊]
   - [ ] doujin2.acgmoe.com/board [],[同人2]
   - [ ] spg-web.sytes.net/PasteChart/insect/ [],[蟲]
   - [ ] l4cs.jpn.org/gikopoi [],[BarGiko]
   - [ ] irc://irc.freenode.net/2cat [],[IRC]
   - [ ] laurenthorizon.weebly.com [],[Sound Horizon]
   - [ ] komica.peroneko.org [],[Minecraft]
   - [ ] www.karlsland.net/t7s [],[T7S]
   - [ ] www.wowhk.org [],[WOW]
   
###### Komica2:
   - [x] komica2.net (二次裡Ａ,遊戲裡,三次元裡,二次裡Ｂ,3D裡,Alicesoft,足襪,YURI,惡搞裡,Figure 裡,成人音聲,改造裡,交易合購裡,玩偶裡,塗鴉裡,壁紙裡,獸裡,寫作裡,YAOI,雜談,小說裡,宣傳裡,精華裡,管理室,寫作資料庫) 
   - [x] 2cat.org (
       - [GIF裡]
       - [動畫裡]
       - [高解析裡] (進不去)
       - [成人玩具]
       - [知識裡]
       - [偽娘裡]
       - [東方裡] (進不去)
      )
   - [ ] p.komica.acg.club.tw (觸手裡)
   - [x] cyber.boguspix.com (機娘裡)
   - [ ] majeur.zawarudo.org (詢問裡)

### 截圖
![1](./doc/1.PNG)、![2](./doc/2.PNG)、![3](./doc/3.PNG)、![4](./doc/4.PNG)、![5](./doc/5.PNG)

 
### 下載
   - [Download](https://github.com/neslxzhen/KomicaViewer/blob/v2/app-debug.apk)

 
### 額外套件 (dependencies)
```gradle
   // 原生
   implementation 'androidx.appcompat:appcompat:1.2.0'
   implementation 'androidx.legacy:legacy-support-v4:1.0.0'
   implementation 'com.google.android.material:material:1.2.0'
   implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
   implementation 'androidx.navigation:navigation-fragment:2.3.0'
   implementation 'androidx.navigation:navigation-ui:2.3.0'
   implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'

   // 第三方
   implementation 'com.amitshekhar.android:android-networking:1.0.2'
   implementation 'org.jsoup:jsoup:1.12.1'
   implementation 'com.github.bumptech.glide:glide:4.9.0'
   implementation 'com.github.clans:fab:1.6.4'
   implementation 'com.google.code.gson:gson:2.8.6'
```
    
### Commit記錄方式
 - Fix: 修復原本的Bug
 - Update: 做了結構上的重大改變，不影響其他功能
 - Add: 新增功能
 - Del: 刪除功能

### 參考來源
 - 搜尋
    - [EhViewer](https://github.com/seven332/EhViewer)
 - 風格
    - [A島匿名板手機端](https://loyea.com/adnmb/download/latest)
 - 功能發想
    - [肉餅臉粗乃丸產生器(alpha)](https://github.com/send-tree-pay/htm170527)
    - [Pitt](https://play.google.com/store/apps/details?id=com.ihad.ptt)
 - 其他
    - [Komica20160704/homu-api](https://homu.homu-api.com/api) and [Repository](https://github.com/Komica20160704/homu-api)
    - [Komica+](https://github.com/TakumaMochizuki/Komica)

### 圖
```
路徑圖
host > board > post
```
```
繼承關係圖
============================
Model:

   Host
    |__Komica2Host
    |__KomicaHost
         |__KomicaTop50Host

   Post
    |__SoraPost
    |    |__MymoePost
    |__SoraBoard
         |__MymoeBoard

============================
View:

   BaseFragment
    |__PostFragment
    |__BoardFragment

============================
ViewModel:

   BaseViewModel
    |__PostViewModel
    |__BoardViewModel
    
============================
```
```
網址結構
getUrl() == getProtocol()+"://"+getHost()+getPath() // http://sora.komica.org/00/pixmicat.php?res=18287039
getLastPathSegment() // https://sora.komica.org/00
```

### 如何新增Host?
 - let your `hostModel` implements `Host` like `KomicaHost`
 - let your `postModel` and `boardModel` implements `Post` like `SoraBoard` and `SoraPost`
 - put your `hostModel` in 34th line of `ProjectUtils.getHosts()`,there are notes `// add host item in there`

### 如何新增Komica聯合站的Host?
 - let your `postModel` and `boardModel` implements `Post`
 - instantiate your `postModel` and `boardModel` in 96th line of `KomicaHost.getSubHosts()`,there are notes `// add komica model item in there`


### Java的語言缺點
 - 缺陷
   - 不能abstract static function 
   // https://stackoverflow.com/questions/370962/why-cant-static-methods-be-abstract-in-java
   - super() or this()只能放在第一個，因此不能先宣告變數並處理後再傳入
   // https://stackoverflow.com/questions/1168345/why-do-this-and-super-have-to-be-the-first-statement-in-a-constructor