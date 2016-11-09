## java正则表达式##
Java 提供了功能强大的正则表达式API，在java.util.regex 包下。本教程介绍如何使用正则表达式API。
###正则表达式

一个正则表达式是一个用于文本搜索的文本模式。换句话说,在文本中搜索出现的模式。例如，你可以用正则表达式搜索网页中的邮箱地址或超链接。
####正则表达式示例
下面是一个简单的Java正则表达式的例子，用于在文本中搜索 http://
>     @Test
	public void test1() {
		String text = "This is the text to be searched for occurrences of the http://pattern.";
		String pattern = ".*http://.*";
		boolean matches = Pattern.matches(pattern, text);
		System.out.println("matches=" + matches);
	}

示例代码实际上没有检测找到的 http:// 是否是一个合法超链接的一部分,如包含域名和后缀(.com,.net 等等)。代码只是简单的查找字符串 http:// 是否出现。
####Java6 中关于正则表达式的API
本教程介绍了Java6 中关于正则表达式的API。
####Pattern (java.util.regex.Pattern)
类 java.util.regex.Pattern 简称 Pattern, 是Java正则表达式API中的主要入口，无论何时,需要使用正则表达式,从Pattern 类开始
####Pattern.matches()
检查一个正则表达式的模式是否匹配一段文本的最直接方法是调用静态方法Pattern.matches(),示例如下:
>     @Test
	public void test2() {
		String text = "This is the text to be searched for occurrences of the pattern.";
		String pattern = ".*is.*";
		boolean matches = Pattern.matches(pattern, text);
		System.out.println("matches=" + matches);
	}
