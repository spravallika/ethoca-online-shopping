package etc.qa.at.steps.bindings;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import etc.qa.at.steps.impl.AccountRegistrationStepsServiceWebImpl;
import etc.qa.at.steps.impl.SearchAndOrderServiceWebImpl;
import etc.qa.at.steps.service.IAccountRegistrationStepsService;
import etc.qa.at.steps.service.ISearchAndOrderStepsService;

public class DIModuleWeb extends AbstractModule {
    @Override
    protected void configure() {
        bind(ISearchAndOrderStepsService.class).to(SearchAndOrderServiceWebImpl.class).in(Singleton.class);
        bind(IAccountRegistrationStepsService.class).to(AccountRegistrationStepsServiceWebImpl.class).in(Singleton.class);
    }
}
