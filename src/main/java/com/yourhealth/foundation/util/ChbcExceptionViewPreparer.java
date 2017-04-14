package com.yourhealth.foundation.util;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.Definition;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.servlet.ServletRequest;

public class ChbcExceptionViewPreparer implements ViewPreparer {

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		ServletRequest req = (ServletRequest)tilesContext;
		String url = req.getRequest().getRequestURI();
		String definitionName = url.substring(url.indexOf("/", 1)+1);
		TilesContainer container = TilesAccess.getContainer(tilesContext.getApplicationContext());
		Definition def = container.getDefinition(definitionName, tilesContext);
		if (def == null) {
			def = container.getDefinition("base", tilesContext);
		}
		attributeContext.inherit(def);
	}

}
