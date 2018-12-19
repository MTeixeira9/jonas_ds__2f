package inatividade;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

import inatividade.InatividadeEvent;

public class InatividadeZirk {
	
	private Bezirk bezirk;

	public InatividadeZirk() {
		BezirkMiddleware.initialize();
		bezirk = BezirkMiddleware.registerZirk("Inatividade Zirk");
	}
	
	//TODO
	

}
