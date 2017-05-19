/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.multimodule.web;

import java.util.Date;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sample.multimodule.domain.entity.Account;
import sample.multimodule.service.api.AccountService;

@Controller
public class WelcomeController {

	@Value("${application.message:Hello World}")
	private String message = "Hello World";

  @Autowired
  protected AccountService accountService;

	@RequestMapping("/")
  public String welcome(Map<String, Object> model) {
    // Trying to obtain 23 account
    Account account = accountService.findOne("23");
    if(account == null){
      // If there's some problem creating account, return show view with error status
      model.put("message", "Error getting account!");
      model.put("account", "");
      return "welcome/show";
    }
    
    // Return show view with 23 account info
    String accountInfo = "Your account number is ".concat(account.getNumber());
    model.put("message", this.message);
    model.put("account", accountInfo);
		return "welcome/show";
	}

	@RequestMapping("foo")
	public String foo(Map<String, Object> model) {
		throw new RuntimeException("Foo");
	}

}
