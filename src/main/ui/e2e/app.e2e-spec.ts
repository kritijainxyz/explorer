import { ExplorerAppPage } from './app.po';

describe('explorer-app App', function() {
  let page: ExplorerAppPage;

  beforeEach(() => {
    page = new ExplorerAppPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
