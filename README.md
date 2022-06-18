# JAVA_Complier
> 编译原理实验，JAVA编写的Java语言子集语法、语义分析器

## 实验目的
1.	强化对系统软件综合工程实现能力、规划能力的训练；
2.	增强对语法分析器、语义分析及代码生成器基本实现技术的理解。

## 实验内容

<div align=center>
    <img src=https://github.com/Robin-WZQ/JAVA_Compiler/blob/main/pic/图片1.png width="500"/>
</div>

- 完成对附录一中文法描述的 Java 源代码的语法分析、语义分析及代码生成；
- 词法分析直接使用词法分析器处理结果（假定已有，不需要另外开发）；
- 通过测试用例的验收。

## 实验过程

### 文法解析
我们看到子集文法中有许多非终结符并无中文释义，在尝试理解文法所描述的语言后，相关释义如下：
- Sw：循环语句 → while (<条件表达式>) 语句；
- Er：条件(比较)表达式 → <表达式>大于<表达式> 或 <表达式>小于<表达式>；
- S：赋值语句 → <标识符> = <表达式>；
- Ex：运算语句 → <标识符> [运算符] <运算语句> 或 <标识符>；
- P：运算符 → 加 减 乘 除。

通过观察可以发现，该子集文法有以下特点：
1.	缺少“开始符”，对于识别JAVA程序来讲，“开始符”应当以“程序”开始；
2.	无法识别关键字，如JAVA语法中的关键字“Public”、“statistic”等无法进行识别，因此在后续语法分析时可以不加考虑；
3.	不包含“if……then”条件转移语句，仅包含循环语句“While”、比较表达式、赋值语句以及运算语句这几个基础语句；
4.	“While”语句中只可包含一个语句，即由于无“{}”只可执行“While”下的一条循环语句；
5.	文法仅可识别整数，如“double”、“float”无法进行识别；
6.	文法存在左公因子、二义性等问题，需要进行文法的修改以得到LL(1)文法。

### 构建LL（1）文法

- 改写后文法：
<div align=center>
    <img src=https://github.com/Robin-WZQ/JAVA_Compiler/blob/main/pic/图片3.png width="500"/>
</div>

<div align=center>
    <img src=https://github.com/Robin-WZQ/JAVA_Compiler/blob/main/pic/图片4.png width="500"/>
</div>

- FIRST集+FOLLOW集：
<div align=center>
    <img src=https://github.com/Robin-WZQ/JAVA_Compiler/blob/main/pic/图片8.png width="500"/>
</div>

- 关系预测表
<div align=center>
    <img src=https://github.com/Robin-WZQ/JAVA_Compiler/blob/main/pic/图片7.png width="500"/>
</div>

### 词法分析结果

<div align=center>
    <img src=https://github.com/Robin-WZQ/JAVA_Compiler/blob/main/pic/pic14.png width="300"/>
</div>

### 语法分析结果

<div align=center>
    <img src=https://github.com/Robin-WZQ/JAVA_Compiler/blob/main/pic/图片13.png width="700"/>
</div>

### 语义分析结果

语法树没搞出来，就弄了个逆波兰表达式：

<div align=center>
    <img src=https://github.com/Robin-WZQ/JAVA_Compiler/blob/main/pic/图片14.png width="300"/>
</div>

## Usage - 使用方法

- 切换到当前目录

```
cd ./out
```

- 词法分析

```
java LexicalAnalyzer
// 输入文件：input.txt
// 输出文件：lex_output.txt
```

- 语法分析

```
java SyntaxAnalyzer
// 输入文件：lex_output.txt
// 输出文件：syn_output.txt
```

- 中间代码生成（逆波兰表达式）

```
java RpnMain
// 输入文件：rpn_input.txt
// 输出文件：rpn_output.txt
```

## Code Tree - 文件说明

| src

----| LexicalAnalyzer.java #  词法分析源代码

----| SyntaxAnalyzer.java # 语法分析源代码

----| RpnMain.java # 中间代码生成源码


<!-- ## 参考

https://github.com/SJ-Z/SyntaxAnalyzer

https://www.dssz.com/1861225.html -->
