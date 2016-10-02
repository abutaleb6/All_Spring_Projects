/**
 * 
 */
package com.nazdaqTechnologies.sebl.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lowagie.text.pdf.codec.Base64;
import com.nazdaqTechnologies.core.message.Message;
import com.nazdaqTechnologies.core.message.MessageContentType;
import com.nazdaqTechnologies.core.message.MessageHeader;
import com.nazdaqTechnologies.core.message.model.Status;
import com.nazdaqTechnologies.core.message.model.StatusType;
import com.nazdaqTechnologies.core.message.processor.json.gson.GsonJsonMessageProcessor;
import com.nazdaqTechnologies.sebl.model.Report;
import com.nazdaqTechnologies.sebl.model.SEBLTask;
import com.nazdaqTechnologies.sebl.model.message.AbstractMessage;
import com.nazdaqTechnologies.sebl.service.SeblReportService;
import com.nazdaqTechnologies.sebl.service.ServiceCoordinator;

@Controller
public class SeblController {
	private static Logger log = LoggerFactory.getLogger(SeblController.class);

	private ServiceCoordinator serviceCoordinator;

	private SeblReportService seblReportService;

	private GsonJsonMessageProcessor messageProcessor;

	public ServiceCoordinator getServiceCoordinator() {
		return serviceCoordinator;
	}

	public void setServiceCoordinator(ServiceCoordinator serviceCoordinator) {
		this.serviceCoordinator = serviceCoordinator;
	}

	public SeblReportService getSeblReportService() {
		return seblReportService;
	}

	public void setSeblReportService(SeblReportService seblReportService) {
		this.seblReportService = seblReportService;
	}

	public GsonJsonMessageProcessor getMessageProcessor() {
		return messageProcessor;
	}

	public void setMessageProcessor(GsonJsonMessageProcessor messageProcessor) {
		this.messageProcessor = messageProcessor;
	}

	@RequestMapping(value = "/jsonRequest", method = RequestMethod.POST)
	@ResponseBody
	public String handleJsonRequest(@RequestBody String json) {
		Message<?> request = null;
		MessageHeader requestHeaders = null;
		String responseString = null;

		Message<?> dataMsg = null;
		Message<?> response = null;

		String errorString = null;

		String serviceName = null;

		List<SEBLTask> task = new ArrayList<SEBLTask>();

		Map<String, Object> statusMsgHeader = new HashMap<String, Object>();
		statusMsgHeader.put(MessageHeader.CONTENT_TYPE, MessageContentType.STATUS);
		log.debug("Recieved Request {}", json);

		try {
			request = messageProcessor.processMessage(json);

			if (request != null && request.getHeader().getContentType() != MessageContentType.EXCEPTION.toString()) {
				requestHeaders = request.getHeader();

				serviceName = requestHeaders.getDestination();

				if (serviceName != null) {

					log.debug("Source [{}] Destination [{}]", requestHeaders.getSource(), serviceName);
					// validating request
					validateRequest(requestHeaders, request);
					// sending message to service coordinator
					dataMsg = serviceCoordinator.service(request);

					if (dataMsg == null) {

						String errorMsg = "no response received from service -> " + serviceName;
						log.error(errorMsg);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(new Status(StatusType.ERROR, errorMsg));

						response = messageProcessor.createResponseMessage(request, statusMsgHeader, statusMsgHeader);

					}
					else {

						if (dataMsg.getHeader().getSource().equals("SA")) {

							responseString = (String) dataMsg.getPayload();
						}
						else {

							List<Status> statusMsgList = new ArrayList<Status>();
							statusMsgList.add(new Status(StatusType.OK));
							Message<List<Status>> statusMsg = messageProcessor.createResponseMessage(request, statusMsgList, statusMsgHeader);

							List<Message<?>> msgBody = new ArrayList<Message<?>>();
							msgBody.add(statusMsg);
							msgBody.add(dataMsg);

							Map<String, Object> finalMsgHeader = new HashMap<String, Object>();
							finalMsgHeader.put(MessageHeader.CONTENT_TYPE, MessageContentType.MULTI);
							response = messageProcessor.createResponseMessage(request, msgBody, finalMsgHeader);
						}

					}
				}

			}
		}
		catch (Exception ex) {
			log.error("error with request {}", ex);
			errorString = ex.getLocalizedMessage();

			List<Status> statusList = new ArrayList<Status>();
			statusList.add(new Status(StatusType.ERROR, errorString));
			response = messageProcessor.createResponseMessage(request, statusList, statusMsgHeader);
		}

		if (!dataMsg.getHeader().getSource().equals("SA")) {
			responseString = messageProcessor.toJson(response);
		}

		log.info("Sending Response {}", responseString);

		return responseString;
	}

	private void validateRequest(MessageHeader requestHeaders, Message<?> msg) throws Exception {
		StringBuffer sb = new StringBuffer();

		if (requestHeaders.getContentType() == null) {
			sb.append("Missing ContentType");
		}

		if (requestHeaders.getActionType() == null) {
			sb.append("Missing ActionType");
		}

		if (sb.length() > 0) {
			throw new Exception(sb.toString());
		}

	}

	// for Report added by Taleb

	@RequestMapping(value = "/jsonReportRequest")
	public void generateRequestedReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JasperPrint jasperPrint = null;
		
		//Integer id = Integer.parseInt(request.getParameter("id"));
		String id = request.getParameter("id");
		log.info("Received GUI request for report");
		log.info("id: {}", id);

		if (id.equals(null) || id.equals("") || id.equals("null")) {
			String serverResponse = "Please Enter a Transection Id";
			response.setContentType("text/html");
			response.getOutputStream().write(((serverResponse)).getBytes("UTF-8"));
			log.debug("\nSending server response: \n{}", serverResponse);
			response.getOutputStream().flush();

		}
		else if (!id.equals(null)) {
			response.setContentType("application/pdf");
			ServletOutputStream outputStream = response.getOutputStream();
			//				JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

			JRPdfExporter pdfExporter = new JRPdfExporter();

			pdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, outputStream);

			// two pdf for cash recap report in one pdf file
			//JasperPrint jasperPrints = new JasperPrint();

			//jasperPrints.add(jasperPrint);
			//jasperPrints.add(reportService.generateReportJasperPrint(reportFormat, reportDate, "cmucashrecap"));
			jasperPrint=seblReportService.getJasperPrintForSebl(id); //added by taleb
			
			pdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);

			pdfExporter.exportReport();

			response.getOutputStream().flush();

		}
		else {

			throw new Exception("unsupported report location requested");
		}

	}

}
