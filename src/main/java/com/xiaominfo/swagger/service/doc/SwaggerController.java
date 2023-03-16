package com.xiaominfo.swagger.service.doc;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcs on 2021/12/22.
 */
@Api
@RestController
public class SwaggerController {

	private final static RestTemplate restTemplate = new RestTemplate();
	/**
	 * 使用 curl <server>/set-api-docs?url=<swagger-api-docs-url> 设置文档
	 *
	 * @param url
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("set-api-docs")
	public void updateApiDocsUrl(@RequestParam("url") String url, HttpServletResponse response, HttpServletRequest request) throws IOException {
		request.getSession().setAttribute("url", url);
		response.sendRedirect("/");
	}

	@GetMapping("swagger-resources")
	public Object resources(@SessionAttribute(value = "url", required = false) String url, HttpServletRequest request) {
		String demoUrl = request.getRequestURI().replaceFirst("swagger-resources", "set-api-docs?url=<swagger-api-docs-url>");
		Assert.hasText(url, "使用 " + demoUrl + " 设置文档");
		List<SwaggerResourcesVo> list = new ArrayList<>();
		list.add(SwaggerResourcesVo.builder()
				.name(url.toLowerCase().replaceFirst("https://", "").replaceFirst("http://", ""))
				.location(url)
				.url("online-api-docs")
				.swaggerVersion("2.0").build());
		return list;
	}

	@GetMapping("/online-api-docs")
	public Object apiDocs(@SessionAttribute(value = "url", required = true) String url) {
		return restTemplate.getForEntity(url, String.class).getBody();
	}

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SwaggerResourcesVo{
        private String name;
        private String url;
        private String swaggerVersion;
        private String location;
    }
}
