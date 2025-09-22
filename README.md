# 论文查重系统

本项目实现了一个基于余弦相似度算法的论文查重工具，针对中文文本进行了分词优化，支持处理大文本文件。可以通过命令行比较两篇论文的相似度，将结果以百分比形式写入指定文件。

## 使用方式

**命令格式：**
bash

java -jar papercheck.jar [orig_file] [suspect_file] [ans_file]

**参数说明：**
- `orig_file`：原文文件路径
- `suspect_file`：待检测论文文件路径  
- `ans_file`：结果输出文件路径

## 效果示例

假设有以下两个文本文件：

**orig.txt**
论文查重是学术规范的重要环节，能够有效检测学术不端行为。

**suspect.txt**
论文查重作为学术规范的关键步骤，可以有效识别学术不端现象。

执行命令：
bash

java -jar papercheck.jar orig.txt suspect.txt result.txt

运行后，result.txt 会输出相似度百分比，例如：
76.83%

## 文件结构
papercheck/

│── src/

│ ├── main/

│ │ └── java/

│ │ └── com/

│ │ └── zheng/

│ │ └── papercheck/

│ │ ├── core/

│ │ │ ├── Calculator.java # 核心计算模块

│ │ │ └── TextProcessor.java # 文本预处理

│ │ ├── io/

│ │ │ └── FileHandler.java # 文件读写

│ │ └── Main.java # 程序入口

│ └── test/

│ └── java/

│ └── com/

│ └── zheng/

│ └── papercheck/

│ └── core/

│ └── CalculatorTest.java # 单元测试

│── target/

│ └── papercheck-1.0.jar # 编译输出

│── pom.xml # Maven配置

│── README.md

## 核心特性

- **智能分词**：针对中英文混合文本优化处理
- **高效计算**：基于余弦相似度算法，准确率高
- **大文件支持**：采用流式处理，支持大文本文件
- **标准化输出**：结果以百分比形式呈现，便于阅读

## 环境要求

- Java 17+
- Maven 3.6+

## 构建方式
bash

mvn clean package -DskipTests

构建完成后，可在 `target/` 目录找到可执行JAR文件。
