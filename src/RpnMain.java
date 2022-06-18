import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

public class RpnMain {
    /**
     * 检查算术表达术括号是否匹配, 语法是否正确
     *
     * @param s 算术表达术
     * @return boolean
     */
    public boolean isMatch(String s) {
        //括号符号栈
        Stack<Character> charStack = new Stack<>();
        //将表达式的字符串转换成数组
        char[] charArray = s.toCharArray();
        for (char aChar : charArray) {
            if (aChar == '(') {
                charStack.push(aChar);
            } else if (aChar == ')') {
                //如果是 ) , 且栈为空则返回 false
                if (charStack.isEmpty()) {
                    return false;
                } else {
                    if (charStack.peek() == '(') {
                        //把栈顶的值删除
                        charStack.pop();
                    }
                }
            }
        }
        return charStack.empty();
    }
    /**
     * 判断是否为操作符 + - * /
     *
     * @param charAt
     * @return boolean
     */
    public boolean isOperator(char charAt) {
        return charAt == '+' || charAt == '-' || charAt == '*' || charAt == '/' || charAt == '>' || charAt == '<';
    }
    /**
     * 根据正确的表达式, 获取逆波兰式
     *
     * @param input
     * @return java.lang.String
     */
    @SuppressWarnings("unchecked")
    public StringBuilder getRpn(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append("RPN: ");
        //运算符栈
        Stack<Character> opStack = new Stack();
        //运算符优先级
        Map<Character, Integer> opMap = new HashMap(5);
        opMap.put('(', 0);
        opMap.put('>', 1);
        opMap.put('<', 1);
        opMap.put('+', 1);
        opMap.put('-', 1);
        opMap.put('*', 2);
        opMap.put('/', 2);
        for (int i = 0; i < input.length(); i++) {
            //如果是'('直接压栈
            if (input.charAt(i) == '(') {
                opStack.push('(');
            } else if (new RpnMain().isOperator(input.charAt(i))) {
                //如果是运算符
                char curOp = input.charAt(i);
                //如果运算符栈是空，就直接压栈
                if (opStack.isEmpty()) {
                    opStack.push(curOp);
                } else if (opMap.get(curOp) > opMap.get(opStack.peek())) {
                    opStack.push(curOp);
                } else {
                    //栈不为空，且运算符的优先级小于等于栈顶元素
                    for (int j = 0; j <= opStack.size(); j++) {
                        //弹出栈内第一个元素
                        char ch = opStack.pop();
                        sb.append(ch);
                        if (opStack.isEmpty()) {
                            opStack.push(curOp);
                            break;
                        } else if (opMap.get(curOp) > opMap.get(opStack.peek())) {
                            opStack.push(curOp);
                            break;
                        }
                    }
                }
            } else if (input.charAt(i) == ')') {
                //如果是')'就把站内'('上的元素都弹出栈
                for (int j = 0; j < opStack.size(); j++) {
                    char c = opStack.pop();
                    if (c == '(') {
                        break;
                    } else {
                        sb.append(c);
                    }
                }
            } else if ('A'<=input.charAt(i)&&input.charAt(i)<='Z'){
                //字母就直接添加
                sb.append(input.charAt(i));
            }else if ('a'<=input.charAt(i)&&input.charAt(i)<='z'){
                //字母就直接添加
                sb.append(input.charAt(i));
            }else if (Character.isDigit(input.charAt(i))){
                //数字
                sb.append(input.charAt(i));
            }else if (input.charAt(i) == ';'){
                //数字
                continue;
            }else {
                return new StringBuilder("表达式含有不可识别字符");
            }
        }
        //把栈内剩余的运算符都弹出站
        for (int i = 0; i <= opStack.size(); i++) {
            sb.append(opStack.pop());
        }
        return sb;
    }
    public static void main(String[] args)  throws IOException {
        RpnMain rpnMain = new RpnMain();
        System.out.println("\n逆波兰表达式，王中琦，1120190892\n");
        
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入测试文件的路径:");
        String fileName = sc.nextLine();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuffer outputBuffer = new StringBuffer(); // 输出到文件的StringBuffer
        String line;
        while((line = br.readLine()) != null){
            if (rpnMain.isMatch(line)) {
                    // 获取逆波兰式
                    System.out.println(rpnMain.getRpn(line));
                    outputBuffer.append(rpnMain.getRpn(line));
                    outputBuffer.append("\n");
                } else {
                    System.out.println("错误！");
                }
        }
        br.close();
       
        System.out.println("请输入中间代码结果文件的保存路径：");
        String outputPath = sc.nextLine();
        // 将StringBuffer的内容输出到文件
        File outputFile = new File(outputPath);
        if (outputFile.exists()) {
            outputFile.delete();
        }
        PrintWriter writer = null;
        try {
            outputFile.createNewFile();
            writer = new PrintWriter(new FileOutputStream(outputFile));
            writer.write(outputBuffer.toString());
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        sc.close();
    }
        // while (true) {
            
        //     System.out.println("==========================\n请输入表达式(按q退出)");
        //     String input = sc.nextLine();
        //     if ("q".equals(input)) {
        //         sc.close();
        //         return;
        //     } else {
        //         if (rpnMain.isMatch(input)) {
        //             System.out.println("生成成功，如下：");
        //             // 获取逆波兰式
        //             System.out.println(rpnMain.getRpn(input));
        //         } else {
        //             System.out.println("错误！按 'q' 退出");
        //         }
        //     }
        // }
}