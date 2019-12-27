/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package swagger2.spring.boot;

import java.net.URL;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;

/**
 * TODO
 * 
 * @author ： <a href="https://github.com/hiwepy">hiwepy</a>
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DemoApplicationTests {

	/**
	 * 生成AsciiDoc的代码片段：
	 * https://blog.csdn.net/qq_35873847/article/details/79191848
	 */
	//@Test
	public void buildAsciiDoc() throws Exception {

		// 输出Ascii格式
		
		/*
		 * 
		 * MarkupLanguage.ASCIIDOC：指定了要输出的最终格式。除了ASCIIDOC之外，还有MARKDOWN和CONFLUENCE_MARKUP
		 * from(new URL("http://localhost:8080/v2/api-docs")：指定了生成静态部署文档的源头配置，可以是这样的URL形式，也可以是符合Swagger规范的String类型或者从文件中读取的流。如果是对当前使用的Swagger项目，我们通过使用访问本地Swagger接口的方式，如果是从外部获取的Swagger文档配置文件，就可以通过字符串或读文件的方式
    	 * toFolder(Paths.get("src/docs/asciidoc/generated")：指定最终生成文件的具体目录位置
		 * 
		 */

		Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()

				.withMarkupLanguage(MarkupLanguage.ASCIIDOC)

				.build();

		Swagger2MarkupConverter.from(new URL("http://localhost:8080/v2/api-docs"))

				.withConfig(config)

				.build()

				.toFolder(Paths.get("src/docs/asciidoc/generated"));

	}

	/**
	 * 生成confluence的代码片段：
	 * https://blog.csdn.net/qq_35873847/article/details/79191971
	 */
	//@Test
	public void buildMarkdown() throws Exception {

		Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()

				.withMarkupLanguage(MarkupLanguage.MARKDOWN)

				.build();

		Swagger2MarkupConverter.from(new URL("http://localhost:8080/v2/api-docs"))

				.withConfig(config)

				.build()

				.toFolder(Paths.get("src/docs/confluence/generated"));

	}
	
	/**
	 * 生成confluence的代码片段：
	 * https://blog.csdn.net/qq_35873847/article/details/79191971
	 */
	//@Test
	public void buildConfluence() throws Exception {

		Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()

				.withMarkupLanguage(MarkupLanguage.CONFLUENCE_MARKUP)

				.build();

		Swagger2MarkupConverter.from(new URL("http://localhost:8080/v2/api-docs"))

				.withConfig(config)

				.build()

				.toFolder(Paths.get("src/docs/confluence/generated"));

	}

}