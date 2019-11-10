package cn.linkpower.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 服务过滤器配置
 * 
 * @author 76519
 *
 */
@Configuration // 申明配置类(@Component 与之区别在于 @Configuration是单例)
public class MyZuulFilter extends ZuulFilter {
	
	//引入日志文件
	private static Logger log = LoggerFactory.getLogger(MyZuulFilter.class);
	
	/**
	 * 代表过滤器类型：<br>
	 * pre：路由之前<br>
	 * routing：路由之时<br>
	 * post： 路由之后<br>
	 * error：发送错误调用<br>
	 */
	@Override
	public String filterType() {
		log.info("---- filterType ----");
		return "pre";
	}
	
	/**
	 * filterOrder：过滤的顺序 <br>
	 * 当请求在一个阶段的时候存在多个多个过滤器时，需要根据该方法的返回值依次执行；数值越小优先级越高
	 */
	@Override
	public int filterOrder() {
		log.info("---- filterOrder ----");
		return 0;
	}
	
	/**
	 * shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。<br>
	 */
	@Override
	public boolean shouldFilter() {
		log.info("---- shouldFilter ----");
		//如果是登陆请求，则 需要返回 false，不会进入 run()中进行拦截判断！！
		//RequestContext ctx = RequestContext.getCurrentContext();
		//HttpServletRequest request = ctx.getRequest();
		//判断  request.getRequestURL().toString() 获取请求 url  是否包含 登陆请求的action
		//包含登陆的则返回false，其他返回true
		return true;
	}
	
	/**
	 * run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问<br>
	 */
	@Override
	public Object run() throws ZuulException {
		log.info("---- run ----");
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("%s >> %s",  request.getMethod(), request.getRequestURL().toString()));
		//获取携带的token数据
		String token = request.getParameter("token");
		if(token == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}

            return null;
        }
        log.info("ok");
		return null;
	}

}
