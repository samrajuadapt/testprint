package com.adapt.banque;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import javax.print.PrintServiceLookup;
import javax.swing.JEditorPane;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LiveController {

	@RequestMapping("/print/{ticket}")
	public @ResponseBody String printTicket(@PathVariable(value = "ticket") String ticket) {
		
		String html = SC.HTML_TOP;
		
		File file = new File("/Users/adaptme/Desktop/Ticket.html");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			html += ticket;
			html += SC.HTML_BOTTOM;
			bw.write(html);
			bw.close();
			
			  JEditorPane editorPane = new JEditorPane();
			  editorPane.setEditable(false);
			  URL urlToPage = file.toURI().toURL();
			  editorPane.setPage(urlToPage);  
			  editorPane.print(null, null, false, PrintServiceLookup.lookupDefaultPrintService(), null, false);

			
		}catch(Exception e) {
			Logger.getLogger("LOG").info(""+e);

		}
		
		
		return "successfully, printed "+ticket;
	}
}
