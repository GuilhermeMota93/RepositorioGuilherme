package com.example.itlog.communication;

import com.example.itlog.responseobjects.GET_API_ClienteLst_Response;
import com.example.itlog.responseobjects.POST_API_ProjectosByCli_Response;

public interface Callback_Interface_2<T, T2> {
	
	void callbackCall(GET_API_ClienteLst_Response t,
			POST_API_ProjectosByCli_Response t2);
}
