package work.beltran.discogsbrowser.api.di.modules;

import work.beltran.discogsbrowser.api.ApiFrontend;
import work.beltran.discogsbrowser.api.network.DiscogsService;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
public class ApiFrontendMockModule extends ApiFrontendModule {
    private ApiFrontend apiFrontend;

    public ApiFrontendMockModule(ApiFrontend apiFrontend) {
        super(null, null);
        this.apiFrontend = apiFrontend;
    }

    @Override
    public ApiFrontend provideApiFrontend(DiscogsService service) {
        return apiFrontend;
    }
}
