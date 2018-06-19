/*
 * Copyright 2018 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import React from 'react';
import PropTypes from 'prop-types';

import { Icon } from 'semantic-ui-react';

import { compose, lifecycle, withState, branch, renderComponent } from 'recompose';
import { connect } from 'react-redux';
import { Loader } from 'semantic-ui-react';

import { LineContainer, LineTo } from 'components/LineTo';
import { mapObject } from 'lib/treeUtils';
import { getPipelineLayoutInformation } from './pipelineUtils';

import PipelineElement from './PipelineElement';
import { ElementPalette } from './ElementPalette';
import RecycleBin from './RecycleBin';

import lineElementCreators from './pipelineLineElementCreators';
import { isActive } from './pipelineUtils';
import { ElementDetails } from './ElementDetails';

import { fetchPipeline } from './pipelineResourceClient';
import { fetchElements, fetchElementProperties } from './elementResourceClient';

const HORIZONTAL_SPACING = 150;
const VERTICAL_SPACING = 50;
const HORIZONTAL_START_PX = 10;
const VERTICAL_START_PX = 10;
const COMMON_ELEMENT_STYLE = {
  position: 'absolute',
};

const withPaletteOpen = withState('isPaletteOpen', 'setPaletteOpen', true);
const withElementDetailsOpen = withState('isElementDetailsOpen', 'setElementDetailsOpen', false);

const PipelineEditor = ({
  pipelineId,
  pipeline,
  pendingElementIdToDelete,
  layoutInformation,
  isPaletteOpen,
  setPaletteOpen,
  elementsByCategory,
  isElementDetailsOpen,
  setElementDetailsOpen,
}) => {
  const togglePaletteOpen = () => setPaletteOpen(!isPaletteOpen);

  const elementStyles = mapObject(layoutInformation, (l) => {
    const index = l.verticalPos - 1;
    const fromTop = VERTICAL_START_PX + index * VERTICAL_SPACING;
    const fromLeft = HORIZONTAL_START_PX + l.horizontalPos * HORIZONTAL_SPACING;

    return {
      ...COMMON_ELEMENT_STYLE,
      top: `${fromTop}px`,
      left: `${fromLeft}px`,
    };
  });

  let className = 'Pipeline-editor';

  if (isPaletteOpen) {
    className += ' Pipeline-editor--palette-open';
  } else {
    className += ' Pipeline-editor--palette-close';
  }

  return (
    <div className={className}>
      <div className="Pipeline-editor__element-palette">
        <ElementPalette />
      </div>

      <button className="Pipeline-editor__palette-toggle" onClick={togglePaletteOpen}>
        {isPaletteOpen ? <Icon name="caret left" /> : <Icon name="caret right" />}
      </button>

      <div className="Pipeline-editor__content">
        <LineContainer
          className="Pipeline-editor__graph"
          lineContextId={`pipeline-lines-${pipelineId}`}
          lineElementCreators={lineElementCreators}
        >
          <div className="Pipeline-editor__recycle-bin">
            <RecycleBin pipelineId={pipelineId} />
          </div>
          <div className="Pipeline-editor__elements">
            {pipeline.pipeline.merged.elements.add
              .filter(element => isActive(pipeline.pipeline, element))
              .map(e => (
                <div key={e.id} id={e.id} style={elementStyles[e.id]}>
                  <PipelineElement
                    pipelineId={pipelineId}
                    elementId={e.id}
                    onClick={() => setElementDetailsOpen(true)}
                  />
                </div>
              ))}
          </div>
          <div className="Pipeline-editor__lines">
            {pipeline.pipeline.merged.links.add
              .map(l => ({ ...l, lineId: `${l.from}-${l.to}` }))
              .map(l => (
                <LineTo
                  lineId={l.lineId}
                  key={l.lineId}
                  fromId={l.from}
                  toId={l.to}
                  lineType="curve"
                />
              ))}
          </div>
        </LineContainer>
        {isElementDetailsOpen ? (
          <ElementDetails
            pipelineId={pipelineId}
            className="Pipeline-editor__details"
            onClose={() => setElementDetailsOpen(false)}
          />
        ) : (
          undefined
        )}
      </div>
    </div>
  );
};

PipelineEditor.propTypes = {
  // Set by owner
  fetchElementsFromServer: PropTypes.bool.isRequired,
  fetchPipelineFromServer: PropTypes.bool.isRequired,
  pipelineId: PropTypes.string.isRequired,

  // redux state
  pipeline: PropTypes.object.isRequired,
  layoutInformation: PropTypes.object.isRequired,
  elementsByCategory: PropTypes.object.isRequired,

  // withPaletteOpen
  isPaletteOpen: PropTypes.bool.isRequired,
  setPaletteOpen: PropTypes.func.isRequired,
};

PipelineEditor.defaultProps = {
  fetchPipelineFromServer: false,
  fetchElementsFromServer: false,
};

export default compose(
  connect(
    (state, props) => {
      const pipeline = state.pipelines[props.pipelineId];
      let layoutInformation;
      if (pipeline) {
        layoutInformation = getPipelineLayoutInformation(pipeline.asTree);
      }

      return {
        elementsByCategory: state.elements.byCategory || {},
        pipeline,
        layoutInformation,
      };
    },
    {
      // action, needed by lifecycle hook below
      fetchPipeline,
      fetchElements,
      fetchElementProperties,
    },
  ),
  lifecycle({
    componentDidMount() {
      if (this.props.fetchElementsFromServer) {
        this.props.fetchElements();
        this.props.fetchElementProperties();
      }
      if (this.props.fetchPipelineFromServer) {
        this.props.fetchPipeline(this.props.pipelineId);
      }
    },
  }),
  branch(props => !props.pipeline, renderComponent(() => <Loader active>Loading Pipeline</Loader>)),
  branch(
    props => !props.pipeline.pipeline,
    renderComponent(() => <Loader active>Loading Pipeline Data</Loader>),
  ),
  branch(
    props => !props.layoutInformation,
    renderComponent(() => <Loader active>Loading Pipeline Layout Information</Loader>),
  ),
  withPaletteOpen,
  withElementDetailsOpen,
)(PipelineEditor);