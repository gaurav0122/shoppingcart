import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';

import { SearchproductService } from './searchproduct.service';

describe('SearchproductService', () => {
  let service: SearchproductService;
  let httpClientSpy:jasmine.SpyObj<HttpClient>;
  beforeEach(() => {
    let httpClientSpyObj = jasmine.createSpyObj(HttpClient,['get','post','delete','get']);
    TestBed.configureTestingModule({
      providers:[SearchproductService,
        {
          provide:HttpClient,
          useValue:httpClientSpyObj
        }]
    });
    service = TestBed.inject(SearchproductService);
    httpClientSpy = TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
