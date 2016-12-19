#转换不同encoding的文本到utf8
- ###juniversalchardet
    - 多年没更新
    - 部分文本检测失败返回null 并没throw Expection
    - 不可靠
- ###icu4j
    - 稳定维护更新
    - 全部测试文本检测正确
```maven
 <dependencies>
        <dependency>
            <groupId>com.ibm.icu</groupId>
            <artifactId>icu4j</artifactId>
            <version>58.2</version>
        </dependency>
        <dependency>
            <groupId>com.googlecode.juniversalchardet</groupId>
            <artifactId>juniversalchardet</artifactId>
            <version>1.0.3</version>
        </dependency>
    </dependencies>
```

   



