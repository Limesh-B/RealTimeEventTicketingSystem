import { TestBed } from '@angular/core/testing';

import { ConfigurationFormService } from './configuration-form.service';

describe('ConfigurationFormService', () => {
  let service: ConfigurationFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConfigurationFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
